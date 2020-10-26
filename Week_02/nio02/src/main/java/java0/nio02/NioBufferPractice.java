package java0.nio02;

import java.io.File;
import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio01
 * @Description: ()
 * @date 2020/10/26 10:02 上午
 */
public class NioBufferPractice {


    private ByteBuffer buffer;

    public static void main(String[] args) {
        // IntBuffer实例
        practiceIntBuffer();

        // Channel实例
        practiceByteBuffer();
    }


    // IntBuffer声明、生产和消费缓冲区数据
    public static void practiceIntBuffer() {
        // 设置IntBuffer缓冲区大小
        IntBuffer intBuffer = IntBuffer.allocate(8);

        // 遍历Buffer长度，将数据写入缓冲区
        for (int i = 0; i < intBuffer.capacity(); i++) {
            int j = 2 * (i + 1);
            intBuffer.put(j);
        }

        // 重置缓冲区位置
        intBuffer.flip();

        // 消费缓冲区数据
        while (intBuffer.hasRemaining()) {
            int j = intBuffer.get();
            System.out.print(intBuffer.position() + " ");
            System.out.print(j + " ");
            System.out.println();
        }

        // 关闭缓冲区
        intBuffer.clear();
    }

    // 从I/O流中获取管道，将管道流数据读入ByteBuffer
    // allocateDirect 使用直接内存
    public static void practiceByteBuffer() {
        try {
            // 文件I/O流处理
            FileInputStream fin = new FileInputStream("./README.md");
            System.out.println(new File("./README.md").exists());

            // 创建文件操作管道
            FileChannel fc = fin.getChannel();

            // 分配ByteBuffer缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocateDirect(10);
            output("初始化", buffer);

            // 先读一下
            fc.read(buffer);
            output("调用 read()", buffer);

            // 准备操作之前，先锁定操作范围
            buffer.flip();
            output("调用 filp()", buffer);

            // 判断有没有可读数据
            while (buffer.remaining() > 0) {
                byte b = buffer.get();
            }
            output("调用 get()", buffer);

            buffer.flip();
            System.out.println(buffer.asCharBuffer().toString());

            // 关闭buffer
            buffer.clear();
            output("调用 clear()", buffer);

            // 关闭channel
            fc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void output(String step, Buffer buffer) {
        System.out.println(step + "  ");
        //容量，数组的个数
        System.out.println("capacity: " + buffer.capacity());
        //当前操作所在的位置，游标
        System.out.println("position: " + buffer.position());
        //锁定至，flip，数组操作流番位索引只能在position - limit之间
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }

}
