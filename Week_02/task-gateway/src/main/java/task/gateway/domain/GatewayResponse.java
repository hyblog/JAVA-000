package task.gateway.domain;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.Charset;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.domain
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 6:27 下午
 */
public class GatewayResponse  {

    public final static String CONTYPE_JOSN = "application/json;";
    public final static String CONTYPE_HTML = "text/html;";


    public static FullHttpResponse toResponse(HttpResponseStatus code, String out, Charset charset, String conType){
        //这只HTTP及请求头信息
        FullHttpResponse response = new DefaultFullHttpResponse(
                // 设置版本为HTTP 1.1
                HttpVersion.HTTP_1_1,
                // 设置相应状态码
                code,
                // 将输出内容编码设置utf8
                Unpooled.wrappedBuffer(out.getBytes(charset))
        );
        response.headers().set("Content-Type", conType);
        return response;
    }
}
