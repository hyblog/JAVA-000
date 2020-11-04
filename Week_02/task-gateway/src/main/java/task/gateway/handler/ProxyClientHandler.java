package task.gateway.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import kotlin.SuspendKt;
import task.gateway.domain.GatewayResponse;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * Created by ipipman on 2020/11/4.
 *
 * @version V1.0
 * @Package task.gateway.handler
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/4 10:31 下午
 */
public class ProxyClientHandler extends ChannelInboundHandlerAdapter {

    private volatile ChannelHandlerContext sCtx;
    private volatile String proxyUrl;

    public ProxyClientHandler(ChannelHandlerContext sCtx, String proxyUrl) {
        this.sCtx = sCtx;
        this.proxyUrl = proxyUrl;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI(proxyUrl);
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString());
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof FullHttpResponse) {
                FullHttpResponse fullHttpResponse = (FullHttpResponse) msg;
                ByteBuf buf = fullHttpResponse.content();
                String result = buf.toString(CharsetUtil.UTF_8);
                FullHttpResponse response = GatewayResponse.toResponse(
                        HttpResponseStatus.FORBIDDEN,
                        result,
                        Charset.defaultCharset(),
                        GatewayResponse.CONTYPE_JOSN
                );
                sCtx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.close();
        }
    }
}
