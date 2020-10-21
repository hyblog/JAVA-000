# 作业四

#### 生产环境总结
**业务背景：**
  - 生产服务器64Gib内存，跑ES Cluster实例。年轻代使用串行GC，老年代使用CMS；

**CMS分析：**
  - 在尖锋流量读写时，会频繁触发YoungGC（平均40ms），
  - 当老年代满足CMSInitiatingOccupancyFraction=75预值后进行CMS垃圾回收，从gc.log进行分析，初始标记和重新标记STW耗时平均在100ms以内。
  - 在CMS并发预清理和并发清除阶段总耗时9s，当前服务器CPU核数为16C，默认-XX:ConcGCThreads=4个（这里应该可以多加几个并发线程来加速GC整体速度）
  - CMS标记-清除后会存留大量的堆空间碎片，碎片积累到一定程度，会触发FullGC（平均50ms），FullGC会退化成串行GC，同时降低了实例吞吐量和低延迟性。

**G1方案思考：**
  - 当前服务器内存空间较大（64Bib），可以使用G1标记-整理-复制垃圾回收器，G1使用小堆块独立区域的概念，利用标记-复制-整理算法，不会产生碎片垃圾，避免尖峰流量时频繁FullGC（在混合回收阶段，会根据垃圾优先级比例最高的进行区域的回收，回收后将存活对象移出后清理垃圾碎片）。
  

##### 1、分析CMS GC日志
------------
```java
//初始化标记阶段
[GC (CMS Initial Mark) [1 CMS-initial-mark: 23575096K(31398336K)] 23683162K(32395136K), 0.0032959 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 

//并发标记阶段
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.207/0.252 secs] [Times: user=1.78 sys=0.15, real=0.25 secs] 

//并发预清理阶段
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.061/0.062 secs] [Times: user=0.19 sys=0.04, real=0.06 secs] 
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 4.096/5.051 secs] [Times: user=6.10 sys=0.61, real=5.05 secs] 
	[GC (CMS Final Remark) [YG occupancy: 553583 K (996800 K)]
	[Rescan (parallel) , 0.0126327 secs]
	[weak refs processing, 0.0116956 secs]
	[class unloading, 0.0345402 secs]
	[scrub symbol table, 0.0098444 secs]
	[scrub string table, 0.0013698 secs]
	
//重新标记阶段
[1 CMS-remark: 23612602K(31398336K)] 24166185K(32395136K), 0.0775826 secs] [Times: user=0.21 sys=0.01, real=0.08 secs]

//并发清理阶段
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 3.892/3.988 secs] [Times: user=6.61 sys=0.61, real=3.98 secs] 

//并发重制阶段
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.062/0.062 secs] [Times: user=0.09 sys=0.01, real=0.07 secs] 

```
------------

##### 2、分析生产环境JVM启动参数
------------
** $ jps -lvm**
```java
jdk1.8.0_65/bin/java
-server																	 //以Server模式运行，YoungGC使用ParallelGC
-XX:+UseConcMarkSweepGC													 //老年代GC使用CMS
-XX:CMSInitiatingOccupancyFraction=75								     //触发老年代GC阈值75%
-XX:+UseCMSInitiatingOccupancyOnly									     //让JVM每次都使用CMSInitiatingOccupancyFraction阈值进行垃圾回收，而不是让JVM自己做决策
-Xss1m																	 //线程栈大小
-Dfile.encoding=UTF-8												     //设置系统字符编码
-XX:-OmitStackTraceInFastThrow											 //关闭省略频繁抛出异常后没有堆栈信息，-server模式下默认开启
-XX:+HeapDumpOnOutOfMemoryError 								         //当JVM发生OOM时，自动生产DUMP文件
-XX:HeapDumpPath=/export/xxx/log/inst-0  						         //指定DUMP文件路径
-XX:ErrorFile=logs/hs_err_pid%p.log 									 //程序崩溃时生成错误日志
-XX:+PrintGCDetails													     //GC时打印日志
-XX:+PrintGCDateStamps 													 //打印GC时的日期
-XX:+PrintTenuringDistribution 										     //打印MinnorGC年龄段对象大小
-XX:+PrintGCApplicationStoppedTime									     //打印GC时STW时间
-Xloggc:logs/gc.log 												     //GC的日志目录
-Xmx31G																	 //最大堆（当前服务器的50%）
-Xms31G																	 //最小堆

```
------------
##### 3、分析生产环境运行时内存使用情况
------------
** $ jstat -gc  [进程号] **

```java
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
110720.0 110720.0 108464.4  0.0   886080.0 885982.2 31398336.0 10272544.7 99936.0 92248.6 13616.0 11411.8 6252373 252748.177 16424  774.638 253522.815
110720.0 110720.0  0.0   73001.8 886080.0 748300.9 31398336.0 10390346.9 99936.0 92248.6 13616.0 11411.8 6252375 252748.303 16424  774.638 253522.942
110720.0 110720.0  0.0   73001.8 886080.0 815299.8 31398336.0 10390346.9 99936.0 92248.6 13616.0 11411.8 6252375 252748.303 16424  774.638 253522.942

说明
【S0C、S1C、SOU、S1U】Survivior中的From和To区域总空间和已使用空间
【EC、EU】Eden区总空间和已使用空间
【OC、OU】老年代总空间和已使用空间
【MC、MU】Metaspcae区总空间和已使用空间
【CCUS、CCSU】类压缩区总空间和已使用空间
【YGC、YGCT】YoungGC次数和总花费时间
【FGC、FGCT】FullGC次数和总花费时间
```

------------
##### 4、分析生产环境内存分配与使用情况
------------
** $ jmap -heap  [进程号] **

```java
Debugger attached successfully.
Server compiler detected.
JVM version is 25.65-b01

using parallel threads in the new generation.
using thread-local object allocation.
Concurrent Mark-Sweep GC                                        //CMS垃圾回收器

Heap Configuration:
   MinHeapFreeRatio         = 40                                //最小堆空闲率
   MaxHeapFreeRatio         = 70                                //最大堆空闲率
   MaxHeapSize              = 33285996544 (31744.0MB)           //最大堆空间
   NewSize                  = 1134100480 (1081.5625MB)          //新生代初始化空间（64位操作系统*16核*13*10）
   MaxNewSize               = 1134100480 (1081.5625MB)          //新生代最大空间
   OldSize                  = 32151896064 (30662.4375MB)        //老年代初始化空间
   NewRatio                 = 2                                 //新生代和老年代比例1/3
   SurvivorRatio            = 8                                 //存活区和Eden区比例1/9
   MetaspaceSize            = 21807104 (20.796875MB)            //非堆元数据区初始化大小
   CompressedClassSpaceSize = 1073741824 (1024.0MB)             //非堆类压缩区初始化大小
   MaxMetaspaceSize         = 17592186044415 MB                 
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
New Generation (Eden + 1 Survivor Space):
   capacity = 1020723200 (973.4375MB)
   used     = 267013624 (254.64403533935547MB)
   free     = 753709576 (718.7934646606445MB)
   26.159258847060595% used
Eden Space:
   capacity = 907345920 (865.3125MB)
   used     = 153636344 (146.51903533935547MB)
   free     = 753709576 (718.7934646606445MB)
   16.93249956973411% used
From Space:
   capacity = 113377280 (108.125MB)
   used     = 113377280 (108.125MB)
   free     = 0 (0.0MB)
   100.0% used
To Space:
   capacity = 113377280 (108.125MB)
   used     = 0 (0.0MB)
   free     = 113377280 (108.125MB)
   0.0% used
concurrent mark-sweep generation:
   capacity = 32151896064 (30662.4375MB)
   used     = 18012256016 (17177.825942993164MB)
   free     = 14139640048 (13484.611557006836MB)
   56.022375725977966% used

30239 interned Strings occupying 3999816 bytes.
```
------------
##### 5、分析生产环境Java线程
------------
** $ jstack -l  [进程号] **
```java
"New I/O worker #620" #1210552 daemon prio=5 os_prio=0 tid=0x00007fd07a822800 nid=0x59fd runnable [0x00007fd3116ed000]
   java.lang.Thread.State: RUNNABLE
        at sun.nio.ch.EPollArrayWrapper.epollWait(Native Method)
        at sun.nio.ch.EPollArrayWrapper.poll(EPollArrayWrapper.java:269)
        at sun.nio.ch.EPollSelectorImpl.doSelect(EPollSelectorImpl.java:79)
        at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
        - locked <0x00007fda7fe8d478> (a sun.nio.ch.Util$2)
        - locked <0x00007fda7fe8d460> (a java.util.Collections$UnmodifiableSet)
        - locked <0x00007fdb337b22a8> (a sun.nio.ch.EPollSelectorImpl)
        at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
        at org.jboss.netty.channel.socket.nio.SelectorUtil.select(SelectorUtil.java:68)
        at org.jboss.netty.channel.socket.nio.AbstractNioSelector.select(AbstractNioSelector.java:409)
        at org.jboss.netty.channel.socket.nio.AbstractNioSelector.run(AbstractNioSelector.java:206)
        at org.jboss.netty.channel.socket.nio.AbstractNioWorker.run(AbstractNioWorker.java:90)
        at org.jboss.netty.channel.socket.nio.NioWorker.run(NioWorker.java:178)
        at org.jboss.netty.util.ThreadRenamingRunnable.run(ThreadRenamingRunnable.java:108)
        at org.jboss.netty.util.internal.DeadLockProofWorker$1.run(DeadLockProofWorker.java:42)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

   Locked ownable synchronizers:
        - <0x00007fd727e60228> (a java.util.concurrent.ThreadPoolExecutor$Worker

说明
【线程状态】
		【NEW】未启动，RUNABLE在虚拟机内执行的
		【BLOCKED】受阻塞的并等待监听器锁
        【WATING】无限等待另外一个线程执行特定的操作
        【TIMED_WATING】有期限的等待另外一个线程执行特定的操作
	    【TERNUBATED】已退出的
【deamon】守护进程
【prio和os_prio】分别代表线程在JVM优先级，操作系统优先级
【tid】内部线程控制结构的java内存地址（16进制）
【nid】线程ID（16进制）
【java.lang.Thread.State】线程栈信息
【Locked ownable synchronizers】可以用户定位锁依赖
		
```