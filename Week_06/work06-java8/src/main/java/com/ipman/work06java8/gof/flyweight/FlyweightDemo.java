package com.ipman.work06java8.gof.flyweight;

import java.util.HashMap;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.flyweight
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 6:58 下午
 */
public class FlyweightDemo {

    public interface Shape {
        void draw();
    }

    //创建实现接口的实体类。
    public static class Circle implements Shape {
        private String color;
        private int x;
        private int y;
        private int radius;

        public Circle(String color) {
            this.color = color;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Circle: Draw() [Color : " + color
                    + ", x : " + x + ", y :" + y + ", radius :" + radius);

        }
    }

    //创建一个工厂，生成基于给定信息的实体类的对象。
    public static class ShapeFactory {
        private static final HashMap<String, Shape> circleMap = new HashMap<>();

        public static Shape getCircle(String color) {
            Circle circle = (Circle) circleMap.get(color);

            if (circle == null) {
                circle = new Circle(color);
                circleMap.put(color, circle);
                System.out.println("Creating circle of color : " + color);
            }

            return circle;
        }
    }

    //使用该工厂，通过传递颜色信息来获取实体类的对象。
    private static final String colors[] =
            { "Red", "Green", "Blue", "White", "Black" };


    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
    private static int getRandomX() {
        return (int)(Math.random()*100 );
    }
    private static int getRandomY() {
        return (int)(Math.random()*100);
    }

    //主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
    public static void main(String[] args) {
        for(int i=0; i < 20; ++i) {
            Circle circle =
                    (Circle)ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }
}
