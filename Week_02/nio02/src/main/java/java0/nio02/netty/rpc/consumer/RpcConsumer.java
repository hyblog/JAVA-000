package java0.nio02.netty.rpc.consumer;

import java0.nio02.netty.rpc.api.IRpcHelloService;
import java0.nio02.netty.rpc.api.IRpcService;
import java0.nio02.netty.rpc.consumer.proxy.RpcProxy;

/**
 * Created by ipipman on 2020/10/27.
 *
 * @version V1.0
 * @Package java0.nio02.netty.rpc.consumer
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/27 4:48 下午
 */
public class RpcConsumer {

    public static void main(String[] args) {

        IRpcHelloService rpcHelloService = RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpcHelloService.hello("ipman"));

        IRpcService service = RpcProxy.create(IRpcService.class);
        System.out.println("8 + 2 = " + service.add(8, 2));
        System.out.println("8 - 2 = " + service.sub(8, 2));
        System.out.println("8 * 2 = " + service.mult(8, 2));
        System.out.println("8 / 2 = " + service.div(8, 2));

    }
}
