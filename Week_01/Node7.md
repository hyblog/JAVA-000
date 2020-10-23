# 作业七

#### AB压测试SrpingBoot接口，Arthas分析

------------

##### 1、使用G1，分析GC性能
**// 内存8Gib**
$ java   -server  -XX:+UseG1GC  -XX:ConcGCThreads=3   -XX:MaxGCPauseMillis=50  -Xms8g -Xmx8g -jar gateway-server-0.0.1-SNAPSHOT.jar  io.github.kimmking.gateway.server.ApiServerApplication

**// 使用长链接，压100000次，40个线程，1次请求约1Mb（new Byte[1024 * 1024]）**
$ ab -k -n 100000 -c40  http://127.0.0.1:8088//api/hello 
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
$  jvm

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

**// 内存8Gib**
$ java -server  -XX:+UseConcMarkSweepGC -XX:ConcGCThreads=3  -XX:MaxGCPauseMillis=50  -Xms8g -Xmx8g -jar gateway-server-0.0.1-SNAPSHOT.jar  io.github.kimmking.gateway.server.ApiServerApplication

**// 使用长链接，压100000次，40个线程，1次请求约1Mb（new Byte[1024 * 1024]）**
$ ab -k -n 100000 -c40  http://127.0.0.1:8088//api/hello 
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
$  jvm

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

***回顾Case1～2： 同在8Gib堆内存大小环境下，用40个线程并发请求了100000次，每次至少生成1Mib堆内存大小 。G1产生GC的频率要比CMS高，G1平均STW时间要比CMS低30%，QPS上CMS更有优势。 **

** QPS优化总结 **
- 方案：增加Young区堆初始化大小，增加并发程数，增加小堆块大小，增加GC年龄阈值，降低GC暂停时间
- 效果：QPS增加了30%，YoungGC平均STW减少2ms，OldGC平均多26ms

**// 启动参数**
$ java -server -XX:+UseG1GC  -XX:ConcGCThreads=5 -XX:MaxGCPauseMillis=15 -Xms8g -Xmx8g -XX:G1HeapRegionSize=30m -XX:InitiatingHeapOccupancyPercent=70 -XX:+UnlockExperimentalVMOptions -XX:G1NewSizePercent=40 -XX:MaxTenuringThreshold=15 -jar gateway-server-0.0.1-SNAPSHOT.jar  io.github.kimmking.gateway.server.ApiServerApplication

**// 使用长链接，压100000次，40个线程，1次请求约1Mb（new Byte[1024 * 1024]）**
$ ab -k -n 100000 -c40  http://127.0.0.1:8088//api/hello 
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

**//使用Arthas分析GC，YoungGC平均11ms，OldGC平均128ms **
$  jvm
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

