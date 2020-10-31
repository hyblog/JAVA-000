package task.endpoint.tomcat.servlet.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.servlet.entity
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 3:39 下午
 */
public class BaseResp {

    private String tomcatRemoteAddr;

    private String tomcatLocalAddr;

    private String tomcatMethod;

    private String tomcatUri;

    private Map<String, List<String>> tomcatParameters;

    private String data;

    public String getTomcatMethod() {
        return tomcatMethod;
    }

    public void setTomcatMethod(String tomcatMethod) {
        this.tomcatMethod = tomcatMethod;
    }

    public String getTomcatUri() {
        return tomcatUri;
    }

    public void setTomcatUri(String tomcatUri) {
        this.tomcatUri = tomcatUri;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<String, List<String>> getTomcatParameters() {
        return tomcatParameters;
    }

    public void setTomcatParameters(Map<String, List<String>> tomcatParameters) {
        this.tomcatParameters = tomcatParameters;
    }

    public String getTomcatRemoteAddr() {
        return tomcatRemoteAddr;
    }

    public void setTomcatRemoteAddr(String tomcatRemoteAddr) {
        this.tomcatRemoteAddr = tomcatRemoteAddr;
    }

    public String getTomcatLocalAddr() {
        return tomcatLocalAddr;
    }

    public void setTomcatLocalAddr(String tomcatLocalAddr) {
        this.tomcatLocalAddr = tomcatLocalAddr;
    }

    @Override
    public String toString() {
        return "BaseResp{" +
                "tomcatRemoteAddr='" + tomcatRemoteAddr + '\'' +
                ", tomcatLocalAddr='" + tomcatLocalAddr + '\'' +
                ", tomcatMethod='" + tomcatMethod + '\'' +
                ", tomcatUri='" + tomcatUri + '\'' +
                ", tomcatParameters=" + tomcatParameters +
                ", data='" + data + '\'' +
                '}';
    }
}
