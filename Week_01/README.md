# 学习笔记

#### 1、JVM字节码技术

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_01/node/Java%E5%AD%97%E8%8A%82%E7%A0%81%E6%8A%80%E6%9C%AF.png)

------------

#### 2、JVM类加载器

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_01/node/JVM%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8.png)

------------

#### 3、JVM内存模型

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_01/node/JVM%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B.png)

------------

#### 4、JVM启动参数

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_01/node/JVM%E5%90%AF%E5%8A%A8%E5%8F%82%E6%95%B0.png)

------------

#### 5、JDK命令行工具

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_01/node/JDK%E5%91%BD%E4%BB%A4%E8%A1%8C%E5%B7%A5%E5%85%B7.png)

------------

#### 6、GC垃圾回收

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_01/node/GC%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6.png)

------------


# 作业一
#### 1、分析字节码，包含基本数据类型、四则运算、对象引用、流程控制
------------
##### Java代码
```java
package Week_01;

/**
 * Created by ipipman on 2020/10/17.
 *
 * @version V1.0
 * @Package Week_01
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/17 12:27 下午
 */
public class Test {


    /**
     * 四则运算+流程控制
     *
     * @param args
     */
    public static void main(String[] args) {
        int num1 = 1;
        int num2 = 10;
        float num3;
        for (int i = 0; i < 3; i++) {
            num1 = num1 + 1;
            num2 = num2 - 1;
            num3 = num1 * num2;
            if (num3 > num2) {
                num3 = num3 / num2;
            }
        }
    }
}
```
------------
##### 编译Java字节码
```java
javac ./Week_01/Test.java
javap -c -v ./Week_01/Test.class 
```
------------
##### Java字节码
```java
Classfile /Users/ipipman/JAVA-000/Week_01/Test.class
  Last modified 2020-10-17; size 422 bytes
  MD5 checksum a49fe32db56789480c59ce59d9dc3643
  Compiled from "Test.java"
public class Week_01.Test
  minor version: 0                                
  major version: 52                              // JVM版本号
  flags: ACC_PUBLIC, ACC_SUPER                   // 类的标示位
Constant pool:                                   // 常量池
   #1 = Methodref          #3.#14                // java/lang/Object."<init>":()V
   #2 = Class              #15                   // Week_01/Test
   #3 = Class              #16                   // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               main
   #9 = Utf8               ([Ljava/lang/String;)V
  #10 = Utf8               StackMapTable
  #11 = Class              #17                   // "[Ljava/lang/String;"
  #12 = Utf8               SourceFile
  #13 = Utf8               Test.java
  #14 = NameAndType        #4:#5                 // "<init>":()V
  #15 = Utf8               Week_01/Test
  #16 = Utf8               java/lang/Object
  #17 = Utf8               [Ljava/lang/String;
{
  public Week_01.Test();                         // 初始化构造器
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                    // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:				            // 行号的表格（源代码行号与字节码行号映射关系）
        line 11: 0

  public static void main(java.lang.String[]);
  descriptor: ([Ljava/lang/String;)V           // 方法描述符（行参类型[String=字符串数组、方法返回类型V=空）
    flags: ACC_PUBLIC, ACC_STATIC              // 方法访问类型（ACC_PUBLIC=公共方法，ACC_STATIC=静态方法）
    Code:
      stack=2, locals=5, args_size=1           // stack=2代表栈的最大深度为2，locals=5代表本地变量表最大槽数为5，args_size=1代表行参数为1
         0: iconst_1                           // 常量数字1，入操作数栈
         1: istore_1                           // 常量数字1，出操作数栈，入本地变量表LocalVariableTable槽位1，代表完成：int num1 = 1
         2: bipush        10                   // 常量数字10，入操作数栈，常量数大于1-5用b[类型]push+操作数，入操作数栈
         4: istore_2                           // 常量数字10，出操作数栈，入本地变量表槽位2，完成：代表int num2 = 10
         5: iconst_0                           // 常量数字0，入操作数栈
         6: istore        4                    // 常量数字0，出操作数栈，入本地变量表槽位4，完成for循环中初始化的：代表int i = 0
         8: iload         4                    // 局部表量表槽位4（int i = 0）出栈，入操作数栈
        10: iconst_3                           // 常量数字3，入操作数栈
        11: if_icmpge     45                   // 比较数值，如果满足条件跳入45行指令return（跳出方法），代表完成： for 中的(i < 3) 时
        14: iload_1                            // 局部标量表槽位1（int num1=1）出栈，入操作数栈
        15: iconst_1                           // 常量数字1，入操作数栈
        16: iadd                               // 操作数栈相加计算，代表：（int num1 = 1）+ （常量数子1）
        17: istore_1                           // 将计算结果1+ 1 = 2从操作数栈，入本地变量表槽位1，代表完成：num1 = num1 + 1
        18: iload_2                            // 局部变量表槽位2（int num2 = 10）出栈，入操作数栈
        19: iconst_1                           // 常量数字1，入操作数栈
        20: isub                               // 操作数栈相减计算，代表：（int num2 = 10）- (常量数字1) 
        21: istore_2                           // 将计算结果10 - 1 = 9从操作数栈，如本地变量表槽位2，代表完成：num2 = num2 - 1
        22: iload_1                            // 局部变量表槽位1出栈，入操作数栈，int num1 = 2
        23: iload_2                            // 局部变量表槽位2出栈，入操作数栈，int num2 = 9
        24: imul                               // 操作数栈相除计算，代表：（int num2 = 9） / (int num1 = 2)
        25: i2f                                // 操作数栈类型转换，i=int类型，2代表to，f代表float类型，值=4.5
        26: fstore_3                           // 操作数栈计算结果4.5出栈，入本地变量表槽位3，代表完成：int num3 = num2 / num1
        27: fload_3                            // 本地变量表槽位3出栈，入操作数栈
        28: iload_2                            // 本地变量表槽位2出栈，入操作数栈
        29: i2f                                // 操作数栈int类型转换float
        30: fcmpl                              // 操作数栈float类型比较
        31: ifle          39                   // 如果不满足if (num3 > num2)条件，跳如39行指令for(i++）
        34: fload_3                            // 本地变量表槽位3出栈，入操作数栈
        35: iload_2                            // 本地变量表槽位2出栈，入操作数栈
        36: i2f                                // 操作数栈int类型转换float
        37: fdiv                               // 操作数栈浮点数除法，代表num2 / num3
        38: fstore_3                           // 操作数栈计算结果num2 / num3出栈，入本地变量表槽位3
        39: iinc          4, 1                 // 本地变量表槽位4数值加1，代表for(i++)
        42: goto          8                    // 跳入第8行指令，即：（int i = 槽位4的数值）
        45: return                             // 方法返回
      LineNumberTable:                         // 行号的表格（源代码行号与字节码行号映射关系）
        line 20: 0                             // 20=源代码第20行，0代表字节码中Code中的第一行iconst_1
        line 21: 2
        line 23: 5
        line 24: 14
        line 25: 18
        line 26: 22
        line 27: 27
        line 28: 34
        line 23: 39
        line 31: 45
      StackMapTable: number_of_entries = 3     //在Jvm中的ClassLoad的验证阶段使用，被类型检测器使用
        frame_type = 255 /* full_frame */
          offset_delta = 8
          locals = [ class "[Ljava/lang/String;", int, int, top, int ]
          stack = []
        frame_type = 255 /* full_frame */
          offset_delta = 30
          locals = [ class "[Ljava/lang/String;", int, int, float, int ]
          stack = []
        frame_type = 249 /* chop */
          offset_delta = 5
}
SourceFile: "Test.java"

```

# 作业二
#### 1、自定义ClassLoader
------------
##### Java代码
```java
package Week_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by ipipman on 2020/10/16.
 *
 * @version V1.0
 * @Package Week_01
 * @Description: (作业二 ： 自定义类加载器)
 * @date 2020/10/16 6:05 下午
 */
public class Node2 extends ClassLoader {

    private static final String HELLO_CLASS_FILE = "./Week_01/Hello.xlass";
    private static final String HELLO_CLASS_NAME = "Hello";
    private static final String HELLO_CLASS_METHOD = "hello";

    public static void main(String[] args) {
        try {
            // 获取自定义加载类Hello
            Class helloClass = new Node2().findClass(HELLO_CLASS_NAME);
            // 反射获取hello方法
            Method helloMethod = helloClass.getMethod(HELLO_CLASS_METHOD);
            // 执行hello方法，输出：Hello, classLoader!
            helloMethod.invoke(helloClass.newInstance());

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义加载类
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 读取Hello.xclass文件到Byte数组
            byte[] helloClassBytes = getContent(HELLO_CLASS_FILE);
            // 将读取到的字节（x=255-x）
            for (int i = 0; i < Objects.requireNonNull(helloClassBytes).length; i++) {
                helloClassBytes[i] = (byte) (255 - helloClassBytes[i]);
            }
            // 加载自定义Hello类
            return defineClass(name, helloClassBytes, 0, helloClassBytes.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    /**
     * 读取文件到Byte数组
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }
}

```

# 作业三
#### 1、图解JVM内存配置参数
------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_01/Node3.jpg)

------------

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
//以Server模式运行，YoungGC使用ParallelGC
-server								   
   
//老年代GC使用CMS
-XX:+UseConcMarkSweepGC

//触发老年代GC阈值75%
-XX:CMSInitiatingOccupancyFraction=75
	  
//让JVM每次都使用CMSInitiatingOccupancyFraction阈值进行垃圾回收，而不是让JVM自己做决策
-XX:+UseCMSInitiatingOccupancyOnly		  

//线程栈大小
-Xss1m		
							  
//设置系统字符编码
-Dfile.encoding=UTF-8
					  
//关闭省略频繁抛出异常后没有堆栈信息，-server模式下默认开启
-XX:-OmitStackTraceInFastThrow			  

//当JVM发生OOM时，自动生产DUMP文件
-XX:+HeapDumpOnOutOfMemoryError
 		  
//指定DUMP文件路径
-XX:HeapDumpPath=/export/xxx/log/inst-0
   
//程序崩溃时生成错误日志
-XX:ErrorFile=logs/hs_err_pid%p.log
 	  
//GC时打印日志
-XX:+PrintGCDetails
						  
//打印GC时的日期
-XX:+PrintGCDateStamps
 					  
//打印MinnorGC年龄段对象大小
-XX:+PrintTenuringDistribution
 			  
//打印GC时STW时间
-XX:+PrintGCApplicationStoppedTime
		  
//GC的日志目录
-Xloggc:logs/gc.log
 					  
//最大堆（当前服务器的50%）
-Xmx31G					
				  
//最小堆
-Xms31G									  

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

# 作业五

##### 1、分析G1
------------
** $ jmap -heap  [进程号] **

```java
Debugger attached successfully.
Server compiler detected.
JVM version is 25.91-b14

using thread-local object allocation.
Garbage-First (G1) GC with 8 thread(s)				            //G1	

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 8589934592 (8192.0MB)
   NewSize                  = 1073741824 (1024.0MB)
   MaxNewSize               = 2147483648 (2048.0MB)
   OldSize                  = 5452592 (5.1999969482421875MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 4194304 (4.0MB)                   //单个区块的大小=8192MB/2048

Heap Usage:
G1 Heap:
   regions  = 2048
   capacity = 8589934592 (8192.0MB)
   used     = 811384720 (773.7967681884766MB)
   free     = 7778549872 (7418.203231811523MB)
   9.44576133042574% used
G1 Young Generation:
Eden Space:
   regions  = 176
   capacity = 1124073472 (1072.0MB)
   used     = 738197504 (704.0MB)
   free     = 385875968 (368.0MB)
   65.67164179104478% used
Survivor Space:
   regions  = 1
   capacity = 4194304 (4.0MB)
   used     = 4194304 (4.0MB)
   free     = 0 (0.0MB)
   100.0% used
G1 Old Generation:
   regions  = 17
   capacity = 7461666816 (7116.0MB)
   used     = 68992912 (65.79676818847656MB)
   free     = 7392673904 (7050.203231811523MB)
   0.9246313685845498% used

32971 interned Strings occupying 3409752 bytes.
```

------------

# 作业六

#### 通过分配大对象，分析各种GC日志

------------

##### 1、分析SerialGC日志
- $ java -XX:+UseSerialGC -Xms1g -Xmx1g -XX:+PrintGCDetails  Week_01/GCLogAnalysis

`在1秒钟内共生成了11517个对象`
`触发了多次YoungGC和1次TenuredGC，共10次GC`
`最后一次GC后堆空间剩余312Mib`
`在1秒钟之内应用线程STW了360ms，STW平均；36ms`

```java
[GC (Allocation Failure) [DefNew: 279616K->34944K(314560K), 0.0518524 secs] 279616K->86719K(1013632K), 0.0518826 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314560K->34943K(314560K), 0.0645709 secs] 366335K->165644K(1013632K), 0.0645949 secs] [Times: user=0.04 sys=0.03, real=0.06 secs] 
[GC (Allocation Failure) [DefNew: 314559K->34944K(314560K), 0.0483373 secs] 445260K->242993K(1013632K), 0.0483617 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314324K->34943K(314560K), 0.0483716 secs] 522374K->321621K(1013632K), 0.0483974 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 313926K->34941K(314560K), 0.0489166 secs] 600603K->401863K(1013632K), 0.0489431 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314557K->34943K(314560K), 0.0515408 secs] 681479K->485996K(1013632K), 0.0515665 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314559K->34944K(314560K), 0.0523351 secs] 765612K->570928K(1013632K), 0.0523596 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314504K->34944K(314560K), 0.0489143 secs] 850488K->651511K(1013632K), 0.0489396 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 314000K->314000K(314560K), 0.0000134 secs][Tenured: 616567K->366694K(699072K), 0.0516839 secs] 930568K->366694K(1013632K), [Metaspace: 2709K->2709K(1056768K)], 0.0517366 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
[GC (Allocation Failure) [DefNew: 279616K->34943K(314560K), 0.0106868 secs] 646310K->447718K(1013632K), 0.0107104 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
生成对象总数：11517
Heap
 def new generation   total 314560K, used 292158K [0x0000000780000000, 0x0000000795550000, 0x0000000795550000)
  eden space 279616K,  91% used [0x0000000780000000, 0x000000078fb2fc58, 0x0000000791110000)
  from space 34944K,  99% used [0x0000000793330000, 0x000000079554ff48, 0x0000000795550000)
  to   space 34944K,   0% used [0x0000000791110000, 0x0000000791110000, 0x0000000793330000)
 tenured generation   total 699072K, used 412774K [0x0000000795550000, 0x00000007c0000000, 0x00000007c0000000)
   the space 699072K,  59% used [0x0000000795550000, 0x00000007ae869a20, 0x00000007ae869c00, 0x00000007c0000000)
 Metaspace       used 2717K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K

```

------------

##### 2、分析ParallelGC日志
- $ java -XX:+UseParallelGC -XX:ParallelGCThreads=7 -Xms1g -Xmx1g -XX:+PrintGCDetails  Week_01/GCLogAnalysis

`当前服务器8C，GC并行线程数这是为7`
`在1秒钟内共生成了12156个对象`
`触发了多次YoungGC和1次TenuredGC，一共触发了22次GC`
`最后一次GC后堆空间剩余167Mib`
`在1秒钟之内应用线程STW了400ms，STW平均：18ms`

-  ParallelGC总结：ParallelGC和SerialGC对象分配速度和总的STW时间性能相似，但是平均STW时间相差近半

```java
[GC (Allocation Failure) [PSYoungGen: 262144K->43502K(305664K)] 262144K->74885K(1005056K), 0.0279695 secs] [Times: user=0.03 sys=0.14, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 305646K->43516K(305664K)] 337029K->154480K(1005056K), 0.0451588 secs] [Times: user=0.05 sys=0.22, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 305660K->43514K(305664K)] 416624K->225754K(1005056K), 0.0314296 secs] [Times: user=0.06 sys=0.13, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 305444K->43508K(305664K)] 487685K->296816K(1005056K), 0.0334147 secs] [Times: user=0.06 sys=0.14, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 305652K->43510K(305664K)] 558960K->372122K(1005056K), 0.0374063 secs] [Times: user=0.06 sys=0.16, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 305654K->43514K(160256K)] 634266K->456255K(859648K), 0.0386807 secs] [Times: user=0.06 sys=0.16, real=0.04 secs] 
[GC (Allocation Failure) [PSYoungGen: 160250K->67942K(232960K)] 572991K->486801K(932352K), 0.0077066 secs] [Times: user=0.04 sys=0.01, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 184678K->88634K(232960K)] 603537K->520885K(932352K), 0.0131203 secs] [Times: user=0.06 sys=0.02, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 205370K->98953K(232960K)] 637621K->545829K(932352K), 0.0156849 secs] [Times: user=0.07 sys=0.03, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 215315K->66512K(232960K)] 662191K->571081K(932352K), 0.0268761 secs] [Times: user=0.04 sys=0.12, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 182761K->41354K(232960K)] 687330K->601859K(932352K), 0.0241222 secs] [Times: user=0.04 sys=0.10, real=0.02 secs] 
[GC (Allocation Failure) [PSYoungGen: 158090K->40671K(232960K)] 718595K->637351K(932352K), 0.0165238 secs] [Times: user=0.03 sys=0.07, real=0.01 secs] 
[Full GC (Ergonomics) [PSYoungGen: 40671K->0K(232960K)] [ParOldGen: 596680K->321187K(699392K)] 637351K->321187K(932352K), [Metaspace: 2710K->2710K(1056768K)], 0.0437248 secs] [Times: user=0.24 sys=0.01, real=0.05 secs] 
[GC (Allocation Failure) [PSYoungGen: 116698K->34947K(232960K)] 437885K->356135K(932352K), 0.0040907 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 151683K->40835K(232960K)] 472871K->393312K(932352K), 0.0072651 secs] [Times: user=0.05 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 157362K->38123K(232960K)] 509839K->428451K(932352K), 0.0079094 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 154622K->36377K(232960K)] 544950K->461456K(932352K), 0.0073689 secs] [Times: user=0.05 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 153113K->42206K(232960K)] 578192K->500649K(932352K), 0.0080772 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 158942K->40695K(232960K)] 617385K->535070K(932352K), 0.0076546 secs] [Times: user=0.05 sys=0.01, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 156878K->37440K(232960K)] 651253K->567623K(932352K), 0.0076901 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 154176K->43181K(232960K)] 684359K->605788K(932352K), 0.0076395 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 159841K->41541K(232960K)] 722448K->644035K(932352K), 0.0104443 secs] [Times: user=0.06 sys=0.01, real=0.01 secs] 
生成对象总数：12984
Heap
 PSYoungGen      total 232960K, used 158277K [0x00000007aab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 116736K, 100% used [0x00000007aab00000,0x00000007b1d00000,0x00000007b1d00000)
  from space 116224K, 35% used [0x00000007b1d00000,0x00000007b4591628,0x00000007b8e80000)
  to   space 116224K, 0% used [0x00000007b8e80000,0x00000007b8e80000,0x00000007c0000000)
 ParOldGen       total 699392K, used 602494K [0x0000000780000000, 0x00000007aab00000, 0x00000007aab00000)
  object space 699392K, 86% used [0x0000000780000000,0x00000007a4c5f940,0x00000007aab00000)
 Metaspace       used 2717K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K

```

------------

##### 3、分析CMS和ParallelGC性能
- $ java -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=3 -XX:MaxGCPauseMillis=50 -Xms1g -Xmx1g -XX:+PrintGCDetails  Week_01/GCLogAnalysis

`当前服务器8C，CMS并发线程数设置为3，最大GC时间50ms，ParNew并行线程数7。ParallelGC并行线程数7`

-  CMS和ParallelGC比较总结：CMS触发GC的频率要比ParallelGC高，但是CMS平均STW时间性能高30%。两者内存的分配速度相差不大（前提是没有FullGC，在FullGC的场景下，ParallelGC性能比CMS高）

```java
// 分析CMS停顿时间和生成对象的速度（平均29ms）
$ java -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=3  -XX:ParallelGCThreads=7 -Xms1g -Xmx1g -XX:-PrintGCDetails  -XX:+PrintGCApplicationStoppedTime    Week_01/GCLogAnalysis

Total time for which application threads were stopped: 0.0364443 seconds, Stopping threads took: 0.0000187 seconds
Total time for which application threads were stopped: 0.0372299 seconds, Stopping threads took: 0.0000124 seconds
Total time for which application threads were stopped: 0.0461317 seconds, Stopping threads took: 0.0000141 seconds
Total time for which application threads were stopped: 0.0469694 seconds, Stopping threads took: 0.0000140 seconds
Total time for which application threads were stopped: 0.0398816 seconds, Stopping threads took: 0.0000128 seconds
Total time for which application threads were stopped: 0.0003847 seconds, Stopping threads took: 0.0000195 seconds
Total time for which application threads were stopped: 0.0399586 seconds, Stopping threads took: 0.0000133 seconds
Total time for which application threads were stopped: 0.0426213 seconds, Stopping threads took: 0.0000131 seconds
Total time for which application threads were stopped: 0.0400097 seconds, Stopping threads took: 0.0000127 seconds
Total time for which application threads were stopped: 0.0491854 seconds, Stopping threads took: 0.0000126 seconds
Total time for which application threads were stopped: 0.0009964 seconds, Stopping threads took: 0.0000175 seconds
Total time for which application threads were stopped: 0.0155227 seconds, Stopping threads took: 0.0000142 seconds
Total time for which application threads were stopped: 0.0001665 seconds, Stopping threads took: 0.0000192 seconds
Total time for which application threads were stopped: 0.0611796 seconds, Stopping threads took: 0.0000160 seconds
Total time for which application threads were stopped: 0.0121581 seconds, Stopping threads took: 0.0000135 seconds
Total time for which application threads were stopped: 0.0001874 seconds, Stopping threads took: 0.0000437 seconds
生成对象总数：13395

//分析ParallelGC停顿时间和生成对象的速度（平均41ms）
$ java -XX:+UseParallelGC -XX:ParallelGCThreads=7 -Xms1g -Xmx1g -XX:-PrintGCDetails  -XX:+PrintGCApplicationStoppedTime    Week_01/GCLogAnalysis

Total time for which application threads were stopped: 0.0817407 seconds, Stopping threads took: 0.0000225 seconds
Total time for which application threads were stopped: 0.0581598 seconds, Stopping threads took: 0.0000118 seconds
Total time for which application threads were stopped: 0.0342807 seconds, Stopping threads took: 0.0000120 seconds
Total time for which application threads were stopped: 0.0358227 seconds, Stopping threads took: 0.0000119 seconds
Total time for which application threads were stopped: 0.0683213 seconds, Stopping threads took: 0.0000141 seconds
Total time for which application threads were stopped: 0.0677843 seconds, Stopping threads took: 0.0000150 seconds
Total time for which application threads were stopped: 0.0086916 seconds, Stopping threads took: 0.0000119 seconds
Total time for which application threads were stopped: 0.0134020 seconds, Stopping threads took: 0.0000126 seconds
Total time for which application threads were stopped: 0.0170977 seconds, Stopping threads took: 0.0000137 seconds
Total time for which application threads were stopped: 0.0304198 seconds, Stopping threads took: 0.0000129 seconds
Total time for which application threads were stopped: 0.0336244 seconds, Stopping threads took: 0.0000149 seconds
Total time for which application threads were stopped: 0.0175039 seconds, Stopping threads took: 0.0000141 seconds
Total time for which application threads were stopped: 0.0731342 seconds, Stopping threads took: 0.0000158 seconds
生成对象总数：12534

```

------------

##### 3、分析G1和CMS性能
- $ java  -XX:+UseG1GC  -XX:ConcGCThreads=3   -XX:MaxGCPauseMillis=50  -Xms1g -Xmx1g -XX:+PrintGCDetails  -XX:-PrintGCApplicationStoppedTime    Week_01/GCLogAnalysis

`当前服务器8C，G1并发线程数设置为3，最大GC时间50ms`

-  G1和CMS比较总结：G1触发GC的频率要比CMS高，但是G1平均STW时间性能快5倍，G1最大STW时间也比CMS平均快60%。两者内存的分配速度相差不大（前提是没有FullGC，在FullGC的场景下，G1性能比CMS高）

```java
//分析G1停顿时间和生成对象的速度（平均6ms）
$ java  -XX:+UseG1GC  -XX:ConcGCThreads=3   -XX:MaxGCPauseMillis=50  -Xms1g -Xmx1g -XX:-PrintGCDetails  -XX:+PrintGCApplicationStoppedTime    Week_01/GCLogAnalysis

Total time for which application threads were stopped: 0.0067171 seconds, Stopping threads took: 0.0000084 seconds
Total time for which application threads were stopped: 0.0065331 seconds, Stopping threads took: 0.0000119 seconds
Total time for which application threads were stopped: 0.0055693 seconds, Stopping threads took: 0.0000107 seconds
... 略 37行
Total time for which application threads were stopped: 0.0082574 seconds, Stopping threads took: 0.0000115 seconds
Total time for which application threads were stopped: 0.0045833 seconds, Stopping threads took: 0.0000122 seconds
生成对象总数：12534


// 分析CMS停顿时间和生成对象的速度（平均29ms）
$ java -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=3  -XX:ParallelGCThreads=7 -Xms1g -Xmx1g -XX:-PrintGCDetails  -XX:+PrintGCApplicationStoppedTime    Week_01/GCLogAnalysis

Total time for which application threads were stopped: 0.0364443 seconds, Stopping threads took: 0.0000187 seconds
Total time for which application threads were stopped: 0.0372299 seconds, Stopping threads took: 0.0000124 seconds
Total time for which application threads were stopped: 0.0461317 seconds, Stopping threads took: 0.0000141 seconds
Total time for which application threads were stopped: 0.0469694 seconds, Stopping threads took: 0.0000140 seconds
Total time for which application threads were stopped: 0.0398816 seconds, Stopping threads took: 0.0000128 seconds
Total time for which application threads were stopped: 0.0003847 seconds, Stopping threads took: 0.0000195 seconds
Total time for which application threads were stopped: 0.0399586 seconds, Stopping threads took: 0.0000133 seconds
Total time for which application threads were stopped: 0.0426213 seconds, Stopping threads took: 0.0000131 seconds
Total time for which application threads were stopped: 0.0400097 seconds, Stopping threads took: 0.0000127 seconds
Total time for which application threads were stopped: 0.0491854 seconds, Stopping threads took: 0.0000126 seconds
Total time for which application threads were stopped: 0.0009964 seconds, Stopping threads took: 0.0000175 seconds
Total time for which application threads were stopped: 0.0155227 seconds, Stopping threads took: 0.0000142 seconds
Total time for which application threads were stopped: 0.0001665 seconds, Stopping threads took: 0.0000192 seconds
Total time for which application threads were stopped: 0.0611796 seconds, Stopping threads took: 0.0000160 seconds
Total time for which application threads were stopped: 0.0121581 seconds, Stopping threads took: 0.0000135 seconds
Total time for which application threads were stopped: 0.0001874 seconds, Stopping threads took: 0.0000437 seconds
生成对象总数：13395

```
------------

# 作业七

#### AB压测试SrpingBoot接口，Arthas分析

------------

##### 1、使用G1，分析GC性能
**// 内存8Gib**
- $ java   -server  -XX:+UseG1GC  -XX:ConcGCThreads=3   -XX:MaxGCPauseMillis=50  -Xms8g -Xmx8g -jar gateway-server-0.0.1-SNAPSHOT.jar  io.github.kimmking.gateway.server.ApiServerApplication

**// 使用长链接，压100000次，40个线程，1次请求约1Mb（new Byte[1024 * 1024]）**
- $ ab -k -n 100000 -c40  http://127.0.0.1:8088//api/hello 
```java
Requests per second:    1529.84 [#/sec] (mean)
Percentage of the requests served within a certain time (ms)
  50%      8
  66%     17
  75%     27
  80%     35
  90%     66
  95%    114
  98%    178
  99%    228
 100%    648 (longest request)

```

**//使用Arthas分析GC，YoungGC平均13ms，G1OldGC平均100ms**
- $ jvm

```java
G1 Young Generation     
name : G1 Young Generation
 [count/time (ms)]           
collectionCount :302
collectionTime : 3931
									   
 G1 Old Generation           
name : G1 Old Generation
 [count/time (ms)]           
collectionCount : 76
collectionTime : 8294
```

------------

##### 2、使用CMS，与G1性能比较，分析GC性能

- 内存8Gib
- $ java -server  -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=3  -XX:MaxGCPauseMillis=50  -Xms8g -Xmx8g -jar gateway-server-0.0.1-SNAPSHOT.jar  io.github.kimmking.gateway.server.ApiServerApplication

- 使用长链接，压100000次，40个线程，1次请求约1Mb（new Byte[1024 * 1024]）
- $ ab -k -n 100000 -c40  http://127.0.0.1:8088//api/hello
 
```java
Requests per second:    2333.91 [#/sec] (mean)
Percentage of the requests served within a certain time (ms)
  50%      7
  66%     11
  75%     21
  80%     25
  90%     48
  95%     67
  98%     92
  99%    111
 100%    396 (longest request)

```

**//使用Arthas分析GC，ParNewGC平均19ms，CMS平均107ms**
- $ jvm

```java
ParNew                          
name : ParNew
 [count/time (ms)]           
collectionCount :617									   
collectionTime : 11921
									   
ConcurrentMarkSweep      
name : ConcurrentMarkSweep
 [count/time (ms)]           
collectionCount : 1                
collectionTime : 107
```

------------

##### 3、优化G1

- 回顾Case1～2： 同在8Gib堆内存大小环境下，用40个线程并发请求了100000次，每次至少生成1Mib堆内存大小 。G1产生GC的频率要比CMS高，G1平均STW时间要比CMS低30%，QPS上CMS更有优势。

- QPS优化总结
- 方案：增加Young区堆初始化大小，增加并发程数，增加小堆块大小，增加GC年龄阈值，降低GC暂停时间
- 效果：QPS增加了30%，YoungGC平均STW减少2ms，OldGC平均多26ms

- 启动参数
- $ java -server -XX:+UseG1GC  -XX:ConcGCThreads=5 -XX:MaxGCPauseMillis=15 -Xms8g -Xmx8g -XX:G1HeapRegionSize=30m -XX:InitiatingHeapOccupancyPercent=70 -XX:+UnlockExperimentalVMOptions -XX:G1NewSizePercent=40 -XX:MaxTenuringThreshold=15 -jar gateway-server-0.0.1-SNAPSHOT.jar  io.github.kimmking.gateway.server.ApiServerApplication

- 使用长链接，压100000次，40个线程，1次请求约1Mb（new Byte[1024 * 1024]）
- $ ab -k -n 100000 -c40  http://127.0.0.1:8088//api/hello 

```java
Requests per second:    2077.16 [#/sec] (mean)
Percentage of the requests served within a certain time (ms)
  50%      6
  66%     10
  75%     14
  80%     20
  90%     50
  95%     83
  98%    148
  99%    202
 100%    613 (longest request)


```

- 使用Arthas分析GC，YoungGC平均11ms，OldGC平均128ms
- $ jvm
```java
G1 Young Generation     
name : G1 Young Generation
 [count/time (ms)]           
collectionCount :153
collectionTime : 1807
									   
 G1 Old Generation           
name : G1 Old Generation
 [count/time (ms)]           
collectionCount : 66
collectionTime : 8481
```

