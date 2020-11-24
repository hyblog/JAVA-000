package com.ipman.work06java8.gof.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.observer
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 9:07 下午
 */
public class ObserverDemo {


    //创建Observer类
    public static abstract class Observer {
        protected Subject subject;

        public abstract void update();
    }


    //创建 Subject 类。
    public static class Subject {
        private List<Observer> observers = new ArrayList<>();
        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }

        public void attach(Observer observer) {
            this.observers.add(observer);
        }

        public void notifyAllObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    //创建实体观察者类。
    public static class BinaryObserver extends Observer {

        public BinaryObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        @Override
        public void update() {
            System.out.println("Binary String: "
                    + subject.getState());
        }
    }

    public static class OctalObserver extends Observer {

        public OctalObserver(Subject subject){
            this.subject = subject;
            this.subject.attach(this);
        }

        @Override
        public void update() {
            System.out.println( "Octal String: "
                    + subject.getState());
        }
    }

    //当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知依赖它的对象。观察者模式属于行为型模式
    public static void main(String[] args) {
        Subject subject = new Subject();

        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
