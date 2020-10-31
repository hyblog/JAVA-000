package task.gateway.server;

import task.endpoint.tomcat.server.TomcatInitConfig;
import task.endpoint.tomcat.servlet.TomcatServlet;
import task.gateway.entity.EndPoIntInfo;
import task.gateway.entity.HeaderFilterInfo;

import java.io.FileInputStream;
import java.util.*;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.server
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 5:50 下午
 */
public class GatewayInitConfig {

    public Properties webxml = new Properties();

    public List<EndPoIntInfo> endPointList = new LinkedList<>();
    public List<String> whiteList = new ArrayList<>();
    public HeaderFilterInfo filterInfo = new HeaderFilterInfo();
    public Integer gatewayPort = 8001;

    public GatewayInitConfig init() {
        //加载endpoint.properties文件，同时初始话ServletMapping对象
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "gateway.properties");
            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".endpoint")) {
                    String[] ipport = webxml.getProperty(key).split(",");
                    for (String s : ipport) {
                        EndPoIntInfo endPoIntInfo = new EndPoIntInfo();
                        endPoIntInfo.setIp(s.split(":")[0]);
                        endPoIntInfo.setPort(Integer.valueOf(s.split(":")[1]));
                        endPointList.add(endPoIntInfo);
                    }
                }
                if (key.endsWith(".whitelist")) {
                    String[] iplist = webxml.getProperty(key).split(",");
                    whiteList.addAll(Arrays.asList(iplist));
                }
                if (key.endsWith(".serverName")){
                    filterInfo.setServerName(webxml.getProperty(key));
                }
                if (key.endsWith(".serverVersion")){
                    filterInfo.setServerVersion(webxml.getProperty(key));
                }
                if (key.endsWith(".gatewayPort")) {
                    gatewayPort = Integer.valueOf(webxml.getProperty(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
