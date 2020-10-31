package task.endpoint.tomcat.servlet;

import io.netty.handler.codec.http.HttpMethod;
import task.endpoint.tomcat.http.TomcatRequest;
import task.endpoint.tomcat.http.TomcatResponse;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.servlet
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 2:48 下午
 */
public abstract class TomcatServlet {

    public void service(TomcatRequest request, TomcatResponse response) throws Exception {
        if (HttpMethod.GET.equals(request.getReq().getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(TomcatRequest request, TomcatResponse response) throws Exception;

    public abstract void doPost(TomcatRequest request, TomcatResponse response) throws Exception;


}
