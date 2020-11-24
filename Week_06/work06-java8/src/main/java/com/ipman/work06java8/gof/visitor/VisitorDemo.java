package com.ipman.work06java8.gof.visitor;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.visitor
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 10:44 下午
 */
public class VisitorDemo {


    //定义一个表示元素的接口。
    public interface ComputerPart {
        public void accept(ComputerPartVisitor computerPartVisitor);
    }

    //创建扩展了上述类的实体类。
    public static class Keyboard implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    public static class Monitor implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    public static class Mouse implements ComputerPart {
        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            computerPartVisitor.visit(this);
        }
    }

    public static class Computer implements ComputerPart {
        ComputerPart[] parts;
        public Computer() {
            parts = new ComputerPart[]{new Mouse(), new Keyboard(), new Monitor()};
        }
        @Override
        public void accept(ComputerPartVisitor computerPartVisitor) {
            for (int i = 0; i < parts.length; i++) {
                parts[i].accept(computerPartVisitor);
            }
            computerPartVisitor.visit(this);
        }
    }

    //定义一个表示访问者的接口。
    public interface ComputerPartVisitor {
        public void visit(Computer computer);

        public void visit(Mouse mouse);

        public void visit(Keyboard keyboard);

        public void visit(Monitor monitor);
    }

    //创建实现了上述类的实体访问者
    public static class ComputerPartDisplayVisitor implements ComputerPartVisitor {
        @Override
        public void visit(Computer computer) {
            System.out.println("Displaying Computer.");
        }

        @Override
        public void visit(Mouse mouse) {
            System.out.println("Displaying Mouse.");
        }

        @Override
        public void visit(Keyboard keyboard) {
            System.out.println("Displaying Keyboard.");
        }

        @Override
        public void visit(Monitor monitor) {
            System.out.println("Displaying Monitor.");
        }
    }


    //我们使用了一个访问者类，它改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。这种类型的设计模式属于行为型模式。根据模式，元素对象已接受访问者对象，这样访问者对象就可以处理元素对象上的操作。
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}


