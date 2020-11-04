package task.gateway.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import task.gateway.pipline.ClientChannelPipline;
import task.gateway.pipline.GatewayChannelPipline;
import task.gateway.server.GatewayInitConfig;

/**
 * Created by ipipman on 2020/11/4.
 *
 * @version V1.0
 * @Package task.gateway.handler
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/4 10:17 下午
 */
public class ClientHandler {

    public static void start(String proxyIp, Integer proxyPort, String url, ChannelHandlerContext sCtx){
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    //自定义Pipline
                    .handler(new ClientChannelPipline(sCtx, url));
            ChannelFuture future = b.connect(proxyIp, proxyPort).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
