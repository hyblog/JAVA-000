package task.endpoint.tomcat;

import task.endpoint.tomcat.server.Tomcat;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 4:50 下午
 */
public class EndpointApp {
    public static void main(String[] args){
        Tomcat tomcat = new Tomcat();
        tomcat.run();
    }
}
