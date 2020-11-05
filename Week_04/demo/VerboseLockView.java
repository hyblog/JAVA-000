package Week_04.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ipipman on 2020/11/4.
 *
 * @version V1.0
 * @Package Week_03.practive
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/4 9:27 上午
 */
public class VerboseLockView {

    /*
    public volatile int volatileVar;
        descriptor: I
        flags: ACC_PUBLIC, ACC_VOLATILE
    */
    public volatile int volatileVar;

    /*
     public java.lang.Integer integerVar;
        descriptor: Ljava/lang/Integer;
        flags: ACC_PUBLIC
     */
    public Integer integerVar = 0;


    public static void main(String[] args) {

        VerboseLockView lockView = new VerboseLockView();
        //锁方法
        lockView.synchronizedFun();
        //锁代码块
        lockView.synchronizedBlock();
        //Java锁
        lockView.reentrantLockFun();

        System.out.println(lockView.volatileVar);
        System.out.println(lockView.integerVar);
    }


    //锁方法
    /**
     public synchronized void synchronizedFun();
         descriptor: ()V
         flags: ACC_PUBLIC, ACC_SYNCHRONIZED
     */
    public synchronized void synchronizedFun() {
        volatileVar++;
    }

    public void synchronizedBlock() {
        //锁对象
        //monitorenter
        synchronized (integerVar) {
            integerVar++;
        }
        //monitorexit
    }

    public void reentrantLockFun() {
        //Java锁
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            integerVar++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
