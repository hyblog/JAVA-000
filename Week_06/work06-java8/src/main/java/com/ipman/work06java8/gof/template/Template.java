package com.ipman.work06java8.gof.template;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.template
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 8:19 下午
 */
public class Template {

    //创建一个抽象类，它的模板方法被设置为 final。
    public static abstract class Game {
        abstract void initialize();

        abstract void startPlay();

        abstract void endPlay();

        //模版
        public final void play() {
            //初始化游戏
            initialize();

            //开始游戏
            startPlay();

            //结束游戏
            endPlay();
        }
    }

    //创建扩展了上述类的实体类。
    public static class Cricket extends Game {
        @Override
        void endPlay() {
            System.out.println("Cricket Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Cricket Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Cricket Game Started. Enjoy the game!");
        }
    }
    public static class Football extends Game {
        @Override
        void endPlay() {
            System.out.println("Football Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Football Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Football Game Started. Enjoy the game!");
        }
    }


    //一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();

        game = new Football();
        game.play();
    }
}
