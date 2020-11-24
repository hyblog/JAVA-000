package com.ipman.work06java8.gof.state;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.state
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 10:22 下午
 */
public class StateDemo {


    //创建一个接口
    public interface State {
        public void doAction(Context context);
    }

    //创建实现接口的实体类。
    public static class StartState implements State {

        public void doAction(Context context) {
            System.out.println("Player is in start state");
            context.setState(this);
        }

        public String toString(){
            return "Start State";
        }
    }

    public static class StopState implements State {

        public void doAction(Context context) {
            System.out.println("Player is in stop state");
            context.setState(this);
        }

        public String toString(){
            return "Stop State";
        }
    }

    //创建 Context 类。
    public static class Context {
        private State state;

        public Context(){
            state = null;
        }

        public void setState(State state){
            this.state = state;
        }

        public State getState(){
            return state;
        }
    }


    //类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。在状态模式中，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象。
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
