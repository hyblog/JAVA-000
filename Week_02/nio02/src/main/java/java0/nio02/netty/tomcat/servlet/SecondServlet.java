package java0.nio02.netty.tomcat.servlet;

import java0.nio02.netty.tomcat.http.GPServlet;
import java0.nio02.netty.tomcat.http.GPTomcatRequest;
import java0.nio02.netty.tomcat.http.GPTomcatResponse;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio02.netty.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/26 3:04 下午
 */
public class SecondServlet extends GPServlet {


    @Override
    public void doGet(GPTomcatRequest request, GPTomcatResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(GPTomcatRequest request, GPTomcatResponse response) throws Exception {
        response.write("This is Second Servlet");
    }
}
