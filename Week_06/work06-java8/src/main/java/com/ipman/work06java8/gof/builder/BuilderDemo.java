package com.ipman.work06java8.gof.builder;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.builder
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 11:16 上午
 */
public class BuilderDemo {


    static class Product {
        private String name;
        private String type;

        public void showProduct() {
            System.out.println("名称：" + name);
            System.out.println("型号：" + type);
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    abstract static class Builder {
        public abstract void setPart(String arg1, String arg2);
        public abstract Product getProduct();
    }

    static class ConcreteBuilder extends Builder {
        private Product product = new Product();

        @Override
        public Product getProduct() {
            return this.product;
        }

        @Override
        public void setPart(String arg1, String arg2) {
            this.product.setName(arg1);
            this.product.setType(arg2);
        }
    }

    //需求：用户去汽车店购买汽车。
    //分析：汽车店根据每个用户的需求提取对应汽车
    public static class Director {
        private Builder builder = new ConcreteBuilder();

        public Product getAProduct() {
            this.builder.setPart("宝马汽车", "X7");
            return builder.getProduct();
        }

        public Product getBProduct() {
            this.builder.setPart("奥迪汽车", "Q5");
            return builder.getProduct();
        }
    }

    //将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
    public static void main(String[] args) {
        Director director = new Director();

        Product product1 = director.getAProduct();
        product1.showProduct();

        Product product2 = director.getBProduct();
        product2.showProduct();
    }
}
