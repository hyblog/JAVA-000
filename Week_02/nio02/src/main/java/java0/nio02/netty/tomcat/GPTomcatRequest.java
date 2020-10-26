package java0.nio02.netty.tomcat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio02.netty.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/26 5:25 下午
 */
public class GPTomcatRequest {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public GPTomcatRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public HttpRequest getReq() {
        return req;
    }


    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return null;
        } else {
            return param.get(0);
        }
    }

}
