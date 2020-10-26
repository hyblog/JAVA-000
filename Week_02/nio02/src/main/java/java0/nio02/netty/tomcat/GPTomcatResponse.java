package java0.nio02.netty.tomcat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio02.netty.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/26 5:30 下午
 */
public class GPTomcatResponse {

    // SocketChannl的封装
    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public GPTomcatResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String out) throws Exception {
        try {
            if (out == null || out.length() == 0) {
                return;
            }
            //这只HTTP及请求头信息
            FullHttpResponse response = new DefaultFullHttpResponse(
                    // 设置版本为HTTP 1.1
                    HttpVersion.HTTP_1_1,
                    // 设置相应状态码
                    HttpResponseStatus.OK,
                    // 将输出内容编码设置utf8
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8"))
            );

            response.headers().set("Content-Type", "text/html;");


            ctx.write(response);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            ctx.flush();
            ctx.close();
        }
    }
}
