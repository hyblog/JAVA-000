package task.endpoint.tomcat.servlet;

import com.alibaba.fastjson.JSON;
import task.endpoint.tomcat.http.TomcatRequest;
import task.endpoint.tomcat.http.TomcatResponse;
import task.endpoint.tomcat.servlet.entity.FirstResp;
import task.endpoint.tomcat.servlet.entity.Result;
import task.endpoint.tomcat.servlet.entity.SecondResp;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.servlet
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 3:54 下午
 */
public class SecondServlet extends TomcatServlet {

    @Override
    public void doGet(TomcatRequest request, TomcatResponse response) throws Exception {
        response.write(toMessage(request), TomcatResponse.CONTYPE_JOSN);
    }

    @Override
    public void doPost(TomcatRequest request, TomcatResponse response) throws Exception {
        doGet(request, response);
    }

    private String toMessage(TomcatRequest request) {
        SecondResp resp = new SecondResp();
        resp.setTomcatRemoteAddr(request.getRemoteAddress());
        resp.setTomcatLocalAddr(request.getLocalAddress());
        resp.setTomcatUri(request.getReq().uri());
        resp.setTomcatMethod(request.getReq().getMethod().name());
        resp.setTomcatParameters(request.getParameters());
        resp.setData("Second Servlet!");

        Result result = new Result();
        result.setCode(0);
        result.setMsg("ok");
        result.setResult(resp);
        return JSON.toJSONString(result);
    }
}
