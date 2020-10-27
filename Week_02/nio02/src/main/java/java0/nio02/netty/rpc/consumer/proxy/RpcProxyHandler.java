package java0.nio02.netty.rpc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by ipipman on 2020/10/27.
 *
 * @version V1.0
 * @Package java0.nio02.netty.rpc.consumer.proxy
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/27 4:27 下午
 */
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object response;

    public Object getResponse() {
        return response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
    }
}
