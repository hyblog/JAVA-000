package com.ipman.work06java8.gof.iterator;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.iterator
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 8:48 下午
 */
public class IteratorDemo {

    //迭代器
    public interface Iterator {
        public boolean hashNext();

        public Object next();
    }

    //容器
    public interface Container {
        public Iterator getIterator();
    }

    //创建实现了 Container 接口的实体类。该类有实现了 Iterator 接口的内部类 NameIterator。
    public static class NameRepository implements Container {
        public String name[] = {"Robert", "John", "Julie", "Lora"};

        //获得迭代器
        @Override
        public Iterator getIterator() {
            return new NameIterator();
        }

        //设置迭代器
        private class NameIterator implements Iterator {
            int index;

            @Override
            public boolean hashNext() {
                if (index < name.length) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                if (this.hashNext()) {
                    return name[index++];
                }
                return null;
            }
        }
    }

    //迭代器模式（Iterator Pattern）是 Java 和 .Net 编程环境中非常常用的设计模式。这种模式用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();

        for (Iterator iter = nameRepository.getIterator(); iter.hashNext(); ) {
            String name = (String) iter.next();
            System.out.println("Name:" + name);
        }
    }


}
