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