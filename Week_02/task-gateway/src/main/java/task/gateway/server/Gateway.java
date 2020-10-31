package task.gateway.server;

import task.endpoint.tomcat.server.Tomcat;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.server
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 5:41 下午
 */
public class Gateway {

    private GatewayInitConfig config;

    private GatewayNettyServer server;

    public Gateway(){
        this.config = new GatewayInitConfig().init();
        this.server = new GatewayNettyServer();
    }

    public void run(){
        server.start(this.config);
    }

    public static void main(String[] args){

        Tomcat tomcat = new Tomcat();
        tomcat.run();

        Gateway gateway = new Gateway();
        gateway.run();
    }

}
