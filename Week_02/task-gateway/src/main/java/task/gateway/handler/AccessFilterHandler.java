package task.gateway.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import task.gateway.domain.GatewayResponse;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.handler
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 6:11 下午
 */
public class AccessFilterHandler extends ChannelInboundHandlerAdapter {

    public List<String> whiteList = new ArrayList<>();

    public AccessFilterHandler(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;

            //白名单验证
            if (!isAccess(ctx, request)) {
                try {
                    FullHttpResponse response = GatewayResponse.toResponse(
                            HttpResponseStatus.FORBIDDEN,
                            HttpResponseStatus.FORBIDDEN.toString(),
                            Charset.defaultCharset(),
                            GatewayResponse.CONTYPE_HTML
                    );
                    ctx.write(response);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    ctx.flush();
                    ctx.close();
                }
            } else {
                ctx.fireChannelRead(msg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    public Boolean isAccess(ChannelHandlerContext ctx, HttpRequest request) {
        InetSocketAddress isAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = isAddress.getAddress().getHostAddress();
        return whiteList.contains(ip);
    }

}
