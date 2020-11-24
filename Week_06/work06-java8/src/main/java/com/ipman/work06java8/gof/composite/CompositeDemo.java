package com.ipman.work06java8.gof.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.composite
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 6:11 下午
 */
public class CompositeDemo {

    //创建 Employee 类，该类带有 Employee 对象的列表。
    public static class Emloyee {
        private String name;
        private String dept;
        private int salary;
        private List<Emloyee> subrodinates;

        //构造函数
        public Emloyee(String name, String dept, int sal){
            this.name = name;
            this.dept = dept;
            this.salary = sal;
            subrodinates = new ArrayList<Emloyee>();
        }

        public void  add(Emloyee e){
            subrodinates.add(e);
        }

        public void remove(Emloyee e){
            subrodinates.remove(e);
        }

        public List<Emloyee> getSubrodinates(){
            return subrodinates;
        }

        @Override
        public String toString() {
            return "Emloyee{" +
                    "name='" + name + '\'' +
                    ", dept='" + dept + '\'' +
                    ", salary=" + salary +
                    ", subrodinates=" + subrodinates +
                    '}';
        }
    }

    //又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。组合模式依据树形结构来组合对象，用来表示部分以及整体层次。这种类型的设计模式属于结构型模式，它创建了对象组的树形结构。
    public static void main(String[] args) {
        Emloyee CEO = new Emloyee("John","CEO", 30000);
        Emloyee headSales = new Emloyee("Robert","Head Sales", 20000);
        Emloyee headMarketing = new Emloyee("Michel","Head Marketing", 20000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        //打印该组织的所有员工
        for (Emloyee headEmployee : CEO.getSubrodinates()) {
            System.out.println(headEmployee);
            for (Emloyee employee : headEmployee.getSubrodinates()) {
                System.out.println(employee);
            }
        }
    }
}
