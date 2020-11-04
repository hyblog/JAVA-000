package task.gateway.pipline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import task.gateway.handler.ProxyClientHandler;

/**
 * Created by ipipman on 2020/11/4.
 *
 * @version V1.0
 * @Package task.gateway.pipline
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/4 10:20 下午
 */
public class ClientChannelPipline extends ChannelInitializer<SocketChannel> {

    private volatile ChannelHandlerContext sCtx;
    private volatile String proxyUrl;

    public ClientChannelPipline(ChannelHandlerContext sCtx, String proxyUrl) {
        this.sCtx = sCtx;
        this.proxyUrl = proxyUrl;
    }

    @Override
    protected void initChannel(SocketChannel client) throws Exception {
        client.pipeline().addLast(new HttpClientCodec());
        client.pipeline().addLast(new HttpObjectAggregator(65536));
        client.pipeline().addLast(new HttpContentDecompressor());
        //请求分发
        client.pipeline().addLast(new ProxyClientHandler(sCtx, proxyUrl));

    }


}
