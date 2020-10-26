package java0.nio02.netty.tomcat;

import java.io.OutputStream;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio02.netty.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/26 2:55 下午
 */
public class GPResponse {

    private OutputStream out;

    public GPResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception {
        //输出也要遵循HTTP
        //状态码返回200
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(s);
        out.write(sb.toString().getBytes());
    }
}
