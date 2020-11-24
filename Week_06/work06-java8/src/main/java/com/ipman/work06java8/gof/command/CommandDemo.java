package com.ipman.work06java8.gof.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.command
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 8:30 下午
 */
public class CommandDemo {

    //创建一个命令接口。
    public interface Order {
        void execute();
    }

    //创建一个请求类。
    public static class Stock {
        private String name = "ABC";
        private int quantity = 10;

        public void buy() {
            System.out.println("Stock [ Name: " + name + "Quantity: " + quantity + " ] bought");
        }

        public void sell() {
            System.out.println("Stock [ Name: " + name + "Quantity: " + quantity + " ] sold");
        }
    }

    //创建实现了 Order 接口的实体类。
    public static class BuyStock implements Order {
        private Stock adStock;

        public BuyStock(Stock adStock) {
            this.adStock = adStock;
        }

        @Override
        public void execute() {
            adStock.buy();
        }
    }

    public static class SellStock implements Order {
        private Stock adStock;

        public SellStock(Stock adStock) {
            this.adStock = adStock;
        }

        @Override
        public void execute() {
            adStock.sell();
        }
    }

    public static class Broker {
        private List<Order> orderList = new ArrayList<>();

        public void takeOrder(Order order){
            orderList.add(order);
        }

        public void placeOrders(){
            for (Order order: orderList) {
                order.execute();
            }
            orderList.clear();
        }
    }

    //是一种数据驱动的设计模式，它属于行为型模式。请求以命令的形式包裹在对象中，并传给调用对象。调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令。
    public static void main(String[] args) {
        Stock abStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abStock);
        SellStock sellStockOrder = new SellStock(abStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }


}
