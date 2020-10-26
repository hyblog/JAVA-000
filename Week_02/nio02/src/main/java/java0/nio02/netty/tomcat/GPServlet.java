package java0.nio02.netty.tomcat;

import io.netty.handler.codec.http.HttpMethod;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio02.netty.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/26 2:46 下午
 */
public abstract class GPServlet {

    public void service(GPTomcatRequest request, GPTomcatResponse response) throws Exception {
        //由service()方法绝对调用doGet和doPost方法
        if (HttpMethod.GET.equals(request.getReq().method())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(GPTomcatRequest request, GPTomcatResponse response) throws Exception;

    public abstract void doPost(GPTomcatRequest request, GPTomcatResponse response) throws Exception;
}
