package task.endpoint.tomcat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import task.endpoint.tomcat.http.TomcatRequest;
import task.endpoint.tomcat.http.TomcatResponse;
import task.endpoint.tomcat.server.Tomcat;
import task.endpoint.tomcat.server.TomcatInitConfig;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.handler
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 4:37 下午
 */
public class ServletChannelHander extends ChannelInboundHandlerAdapter {

    private TomcatInitConfig config;

    public ServletChannelHander(TomcatInitConfig config) {
        this.config = config;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;

            TomcatRequest request = new TomcatRequest(ctx, req);
            TomcatResponse response = new TomcatResponse(ctx, req);

            String url = request.getReq().uri();
            if (config.servletMapping.containsKey(url)) {
                config.servletMapping.get(url).service(request, response);
            } else {
                response.write("404 - Not Found", TomcatResponse.CONTYPE_HTML);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
