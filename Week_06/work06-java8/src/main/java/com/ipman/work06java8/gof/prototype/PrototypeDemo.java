package com.ipman.work06java8.gof.prototype;

import com.google.common.collect.HashBiMap;
import lombok.ToString;
import org.mockito.internal.creation.SuspendMethod;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;
import java.util.Hashtable;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.prototype
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 11:34 上午
 */
public class PrototypeDemo {

    //创建一个实现了 Cloneable 接口的抽象类。Shape（implements Cloneable）
    @ToString
    public abstract static class Shape implements Cloneable {
        private String id;
        protected String type;

        public abstract void draw();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public Object clone() {
            Object object = null;
            try {
                object = super.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("--" + e.getMessage());
            }
            return object;
        }
    }

    //创建扩展了上面抽象类的实体类。Circle、Rectangle、Square
    public static class Circle extends Shape {
        public Circle() {
            this.type = "Circle";
        }

        @Override
        public void draw() {
            System.out.println("---Inside Circle::draw() method.");
        }
    }

    //创建一个类，从数据库获取实体类，并把它们存储在一个 Hashtable 中。ShapeCache
    public static class ShapCache {
        private static Hashtable<String, Shape> shapeHashtable = new Hashtable<>();

        public static Shape getShape(String shapeId) {
            Shape shapeChache = shapeHashtable.get(shapeId);
            return (Shape) shapeChache.clone();
        }

        // 对每种形状都运行数据库查询，并创建该形状
        // shapeMap.put(shapeKey, shape);
        // 例如，我们要添加三种形状
        public static void loadCache(String id) {
            Circle circle = new Circle();
            circle.setId(id);
            shapeHashtable.put(circle.getId(), circle);
        }

    }

    //原型模式（Prototype Pattern）是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
    public static void main(String[] args) {
        ShapCache.loadCache("1");
        Shape shapeCache1 = ShapCache.getShape("1");
        Shape shapeCache2 = ShapCache.getShape("1");
        Shape shapeCache3 = ShapCache.getShape("1");
        System.out.println(shapeCache1);
        System.out.println(shapeCache1.hashCode());
        System.out.println(shapeCache2);
        System.out.println(shapeCache2.hashCode());
        System.out.println(shapeCache3);
        System.out.println(shapeCache3.hashCode());
        ShapCache.loadCache("2");
        System.out.println(shapeCache1);
        System.out.println(shapeCache1.hashCode());
        System.out.println(shapeCache2);
        System.out.println(shapeCache2.hashCode());
        System.out.println(shapeCache3);
        System.out.println(shapeCache3.hashCode());
    }


}
