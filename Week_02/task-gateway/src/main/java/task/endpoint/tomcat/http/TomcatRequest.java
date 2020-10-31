package task.endpoint.tomcat.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.http
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 2:50 下午
 */
public class TomcatRequest {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public TomcatRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public HttpRequest getReq() {
        return req;
    }

    //获取url全部参数
    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == name) {
            return null;
        } else {
            return param.get(0);
        }
    }

    //获取端口号
    public String getRemoteAddress(){
        InetSocketAddress isAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        int port = isAddress.getPort();
        String ip = isAddress.getAddress().getHostAddress();
        return  ip + ":" + port;
    }

    public String getLocalAddress(){
        InetSocketAddress isAddress = (InetSocketAddress) ctx.channel().localAddress();
        int port = isAddress.getPort();
        String ip = isAddress.getAddress().getHostAddress();
        return  ip + ":" + port;
    }




}
