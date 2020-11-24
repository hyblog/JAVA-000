package com.ipman.work06java8.gof.mediator;

import java.util.Date;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.mediator
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 9:27 下午
 */
public class MediatorDemo {

    //创建中介类。
    public static class ChatRoom {
        public static void showMessage(User user, String message) {
            System.out.println(new Date().toString()
                    + " [" + user.getName() + "] : " + message);
        }
    }

    //创建 user 类。
    public static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String name) {
            this.name = name;
        }

        public void sendMessage(String message) {
            ChatRoom.showMessage(this, message);
        }
    }


    //是用来降低多个对象和类之间的通信复杂性。这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护。中介者模式属于行为型模式。
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
