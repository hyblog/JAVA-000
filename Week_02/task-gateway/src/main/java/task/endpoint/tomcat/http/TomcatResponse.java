package task.endpoint.tomcat.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.http
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 3:01 下午
 */
public class TomcatResponse {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public final static String CONTYPE_JOSN = "application/json;";
    public final static String CONTYPE_HTML = "text/html;";

    public TomcatResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    //返回数据
    public void write(String out, String conType) {
        try {
            if (out == null || out.length() == 0) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes(Charset.defaultCharset())) //UTF-8
            );
            response.headers().set("Content-Type", conType);
            ctx.write(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
            ctx.close();
        }
    }
}
