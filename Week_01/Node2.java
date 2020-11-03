package Week_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

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
