# 作业一（选填）
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
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #3.#14         // java/lang/Object."<init>":()V
   #2 = Class              #15            // Week_01/Test
   #3 = Class              #16            // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               main
   #9 = Utf8               ([Ljava/lang/String;)V
  #10 = Utf8               StackMapTable
  #11 = Class              #17            // "[Ljava/lang/String;"
  #12 = Utf8               SourceFile
  #13 = Utf8               Test.java
  #14 = NameAndType        #4:#5          // "<init>":()V
  #15 = Utf8               Week_01/Test
  #16 = Utf8               java/lang/Object
  #17 = Utf8               [Ljava/lang/String;
{
  public Week_01.Test();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 11: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=5, args_size=1
         0: iconst_1
         1: istore_1
         2: bipush        10
         4: istore_2
         5: iconst_0
         6: istore        4
         8: iload         4
        10: iconst_3
        11: if_icmpge     45
        14: iload_1
        15: iconst_1
        16: iadd
        17: istore_1
        18: iload_2
        19: iconst_1
        20: isub
        21: istore_2
        22: iload_1
        23: iload_2
        24: imul
        25: i2f
        26: fstore_3
        27: fload_3
        28: iload_2
        29: i2f
        30: fcmpl
        31: ifle          39
        34: fload_3
        35: iload_2
        36: i2f
        37: fdiv
        38: fstore_3
        39: iinc          4, 1
        42: goto          8
        45: return
      LineNumberTable:
        line 20: 0
        line 21: 2
        line 23: 5
        line 24: 14
        line 25: 18
        line 26: 22
        line 27: 27
        line 28: 34
        line 23: 39
        line 31: 45
      StackMapTable: number_of_entries = 3
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



