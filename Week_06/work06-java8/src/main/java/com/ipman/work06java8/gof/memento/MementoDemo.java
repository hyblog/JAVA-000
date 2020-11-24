package com.ipman.work06java8.gof.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.memento
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 9:57 下午
 */
public class MementoDemo {

    //创建 Memento 类。
    public static class Memento {
        private String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return this.state;
        }
    }

    //创建 Originator 类。
    public static class Originator {

        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public Memento saveStateToMemento() {
            return new Memento(state);
        }

        public void getStateFromMemento(Memento memento) {
            state = memento.getState();
        }
    }

    //创建 CareTaker 类。
    public static class CareTaker {
        private List<Memento> mementoList = new ArrayList<>();

        public void add(Memento state) {
            mementoList.add(state);
        }

        public Memento get(int index) {
            return mementoList.get(index);
        }
    }


    //保存一个对象的某个状态，以便在适当的时候恢复对象。备忘录模式属于行为型模式
    public static void main(String[] args) {

        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());

    }

}
