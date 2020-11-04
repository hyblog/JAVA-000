package task.gateway.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.gateway.client.ClientHandler;
import task.gateway.domain.GatewayResponse;
import task.gateway.entity.EndPoIntInfo;
import task.gateway.server.GatewayInitConfig;
import task.gateway.utils.HttpUtil;

import java.lang.management.ThreadInfo;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.handler
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 7:01 下午
 */
public class DispatchCoreHandler extends ChannelInboundHandlerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(DispatchCoreHandler.class);

    public GatewayInitConfig config = new GatewayInitConfig();

    public DispatchCoreHandler(GatewayInitConfig config) {
        this.config = config;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
//            //代理分发（Ok HTTP）
//            HttpRequest request = (HttpRequest) msg;
//            try {
//                FullHttpResponse response = GatewayResponse.toResponse(
//                        HttpResponseStatus.FORBIDDEN,
//                        randomInvoke(request.getUri()),
//                        Charset.defaultCharset(),
//                        GatewayResponse.CONTYPE_JOSN
//                );
//                if (null != config.filterInfo) {
//                    response.headers().set("Server",
//                            config.filterInfo.getServerName() + " " + config.filterInfo.getServerVersion());
//                }
//                logger.info(response.toString());
//                ctx.write(response);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                ctx.flush();
//                ctx.close();
//            }

            //代理分发（Netty）
            HttpRequest request = (HttpRequest) msg;
            try {
                EndPoIntInfo endPoIntInfo = config.endPointList.get((int) (Math.random() * config.endPointList.size()));
                ClientHandler.start(
                        endPoIntInfo.getIp(),
                        endPoIntInfo.getPort(),
                        request.getUri(),
                        ctx
                );
            } catch (Exception e) {
                ctx.flush();
                ctx.close();
                e.printStackTrace();
            }

        }
    }

    public String randomInvoke(String url) {
        return invokeHttpProxy(
                config.endPointList.get((int) (Math.random() * config.endPointList.size())), url
        );
    }

    public String invokeHttpProxy(EndPoIntInfo endPoIntInfo, String url) {
        String path = "http://" + endPoIntInfo.getIp() + ":" + endPoIntInfo.getPort() + url;
        System.out.println(path);
        return HttpUtil.doHttpGet(path, "Mozilla/5.0");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
