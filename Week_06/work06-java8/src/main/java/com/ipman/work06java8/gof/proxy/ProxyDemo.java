package com.ipman.work06java8.gof.proxy;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.proxy
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 7:11 下午
 */
public class ProxyDemo {

    //创建一个接口
    public interface Image {
        void display();
    }


    //创建实现接口的实体类。
    public static class RealImage implements Image {

        private String fileName;

        public RealImage(String fileName){
            this.fileName = fileName;
        }

        @Override
        public void display() {
            System.out.println("Displaying " + fileName);
        }

    }


    public static class ProxyImage implements Image{

        private RealImage realImage;
        private String fileName;

        public ProxyImage(String fileName){
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if(realImage == null){
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }

    //一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
    }
}
