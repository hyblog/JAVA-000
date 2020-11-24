package com.ipman.work06java8.gof.bridge;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.bridge
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 2:45 下午
 */
public class BridgeDemo {

    //创建桥接实现接口。
    public interface DrawAPI {
        void drawCircle(int radius, int x, int y);
    }

    //创建实现了 DrawAPI 接口的实体桥接实现类。
    public static class RedCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: red, radius: "
                    + radius + ", x: " + x + ", " + y + "]");
        }
    }

    public static class GreenCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: green, radius: "
                    + radius + ", x: " + x + ", " + y + "]");
        }
    }

    //使用 DrawAPI 接口创建抽象类 Shape。
    public abstract static class Shape {
        protected DrawAPI drawAPI;

        protected Shape(DrawAPI drawAPI) {
            this.drawAPI = drawAPI;
        }

        public abstract void draw();
    }

    //创建实现了 Shape 抽象类的实体类。
    public static class Circle extends Shape {
        private int x, y, radius;

        public Circle(int x, int y, int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        public void draw(){
            this.drawAPI.drawCircle(radius, x, y);
        }
    }

    //是用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
    public static void main(String[] args) {
        Shape readCircle = new Circle(1, 1, 1, new RedCircle());
        Shape greenCircle = new Circle(2, 2, 2, new GreenCircle());

        readCircle.draw();
        greenCircle.draw();
    }
}
