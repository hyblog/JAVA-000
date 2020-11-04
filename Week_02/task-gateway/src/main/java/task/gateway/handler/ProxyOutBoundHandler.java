package task.gateway.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by ipipman on 2020/11/4.
 *
 * @version V1.0
 * @Package task.gateway.handler
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/4 10:56 下午
 */
public class ProxyOutBoundHandler  extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }
}
