package java0.nio02.netty.tomcat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import java0.nio02.netty.tomcat.http.GPServlet;
import java0.nio02.netty.tomcat.http.GPTomcatRequest;
import java0.nio02.netty.tomcat.http.GPTomcatResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio02.netty.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/26 5:21 下午
 */
public class GPTomcatHandler extends ChannelInboundHandlerAdapter {

    private Map<String, GPServlet> servletMapping = new HashMap<String, GPServlet>();


    public GPTomcatHandler(Map<String, GPServlet> servletMapping) {
        this.servletMapping = servletMapping;
    }

    public GPTomcatHandler(){
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(msg);

        if (msg instanceof HttpRequest) {

            HttpRequest req = (HttpRequest) msg;

            // 转交给Request实现
            GPTomcatRequest request = new GPTomcatRequest(ctx, req);
            // 转交给Response实现
            GPTomcatResponse response = new GPTomcatResponse(ctx, req);

            // 实际业务
            String url = request.getReq().uri();
            if (servletMapping.containsKey(url)) {
                servletMapping.get(url).service(request, response);
            } else {
                response.write("404 - Not Found");
            }
        }
    }
}
