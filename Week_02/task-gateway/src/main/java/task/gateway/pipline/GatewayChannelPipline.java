package task.gateway.pipline;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import task.gateway.handler.AccessFilterHandler;
import task.gateway.handler.DispatchCoreHandler;
import task.gateway.server.GatewayInitConfig;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.pipline
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 6:04 下午
 */
public class GatewayChannelPipline extends ChannelInitializer<SocketChannel> {

    private GatewayInitConfig config;

    public GatewayChannelPipline(GatewayInitConfig config) {
        this.config = config;
    }

    @Override
    protected void initChannel(SocketChannel client) throws Exception {
        //http解码
        client.pipeline().addLast(new HttpRequestDecoder());
        //http编码
        client.pipeline().addLast(new HttpResponseEncoder());

        //TODO：安全过滤器（IP白名单）
        if (null != config.whiteList && config.whiteList.size() > 0) {
            client.pipeline().addLast(new AccessFilterHandler(config.whiteList));
        }
        //TODO：请求转发（random）
        if (null != config.endPointList && config.endPointList.size() > 0){
            client.pipeline().addLast(new DispatchCoreHandler(config));
        }
    }
}
