package com.ipman.work06java8.gof.decorator;


/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.decorator
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 3:21 下午
 */
public class DecoratorDemo {

    //创建一个接口
    public interface Shape {
        void draw();
    }

    //创建实现接口的实体类。
    public static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Shape: Rectangle");
        }
    }

    public static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Shape: Circle");
        }
    }

    //创建实现了 Shape 接口的抽象装饰类。
    public abstract static class ShapeDecorator implements Shape {
        protected Shape decoratedShape;

        public ShapeDecorator(Shape decoratedShape){
            this.decoratedShape = decoratedShape;
        }
    }

    //创建扩展了 ShapeDecorator 类的实体装饰类。
    public static class RedShapeDecorator extends ShapeDecorator {
        public RedShapeDecorator(Shape decoratedShape){
            super(decoratedShape);
        }

        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }

        private void setRedBorder(Shape decoratedShape){
            System.out.println("Border Color: Red");
        }
    }


    //允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();

    }
}
