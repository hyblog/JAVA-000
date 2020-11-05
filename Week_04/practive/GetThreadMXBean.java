package Week_04.practive;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by ipipman on 2020/11/3.
 *
 * @version V1.0
 * @Package Week_03.practive
 * @Description: (练习 - 通过JMX获取Java线程)
 * @date 2020/11/3 7:51 下午
 */
public class GetThreadMXBean {

    //通过JMX获取Java线程
    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            /*
                [4] Signal Dispatcher     // 分发处理发送给JVM信号的线程
                [3] Finalizer             // 调用对象finalizer方法的线程
                [2] Reference Handler     // 清除Reference的线程
                [1] main                  // 用户程序入口线程
             */
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }

    }

}