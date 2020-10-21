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

# 作业五

##### 1、分析G1
------------
** $ jmap -heap  [进程号] **

```java
Debugger attached successfully.
Server compiler detected.
JVM version is 25.91-b14

using thread-local object allocation.
Garbage-First (G1) GC with 8 thread(s)				               //G1	

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
   G1HeapRegionSize         = 4194304 (4.0MB)                      //单个区块的大小=8192MB/2048

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