# 作业六

#### 通过分配大对象，分析各种GC日志

------------

##### 1、分析SerialGC日志
$ java -XX:+UseSerialGC -Xms1g -Xmx1g -XX:+PrintGCDetails  Week_01/GCLogAnalysis

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
$ java -XX:+UseParallelGC -XX:ParallelGCThreads=7 -Xms1g -Xmx1g -XX:+PrintGCDetails  Week_01/GCLogAnalysis

`当前服务器8C，GC并行线程数这是为7`
`在1秒钟内共生成了12156个对象`
`触发了多次YoungGC和1次TenuredGC，一共触发了22次GC`
`最后一次GC后堆空间剩余167Mib`
`在1秒钟之内应用线程STW了400ms，STW平均：18ms`

** ParallelGC总结：ParallelGC和SerialGC对象分配速度和总的STW时间性能相似，但是平均STW时间相差近半 **

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
$ java -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=3 -XX:MaxGCPauseMillis=50 -Xms1g -Xmx1g -XX:+PrintGCDetails  Week_01/GCLogAnalysis

`当前服务器8C，CMS并发线程数设置为3，最大GC时间50ms，ParNew并行线程数7。ParallelGC并行线程数7`

** CMS和ParallelGC比较总结：CMS触发GC的频率要比ParallelGC高，但是CMS平均STW时间性能高30%。两者内存的分配速度相差不大（前提是没有FullGC，在FullGC的场景下，ParallelGC性能比CMS高） **

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
$ java  -XX:+UseG1GC  -XX:ConcGCThreads=3   -XX:MaxGCPauseMillis=50  -Xms1g -Xmx1g -XX:+PrintGCDetails  -XX:-PrintGCApplicationStoppedTime    Week_01/GCLogAnalysis

`当前服务器8C，G1并发线程数设置为3，最大GC时间50ms`

** G1和CMS比较总结：G1触发GC的频率要比CMS高，但是G1平均STW时间性能快5倍，G1最大STW时间也比CMS平均快60%。两者内存的分配速度相差不大（前提是没有FullGC，在FullGC的场景下，G1性能比CMS高） **

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
