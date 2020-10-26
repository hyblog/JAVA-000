package io.github.kimmking.netty.server;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class NettyServerApplication {

    private short value;

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8088);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        Deque a = new LinkedList<Integer>();
    }
}
