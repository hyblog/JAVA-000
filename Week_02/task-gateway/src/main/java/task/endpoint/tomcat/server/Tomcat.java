package task.endpoint.tomcat.server;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.server
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 4:09 下午
 */
public class Tomcat {

    private TomcatInitConfig config;
    private TomcatNettyServer server;

    public Tomcat() {
        config = new TomcatInitConfig().init(); //初始化配置
        server = new TomcatNettyServer();   //初始化
    }

    public void run() {
        System.out.println(config.portList);
        System.out.println(config.servletMapping);
        server.start(config);
    }
}
