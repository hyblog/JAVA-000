package task.endpoint.tomcat.server;

import task.endpoint.tomcat.servlet.TomcatServlet;

import java.io.FileInputStream;
import java.util.*;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.endpoint.tomcat.server
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 4:19 下午
 */
public class TomcatInitConfig {

    public List<Integer> portList = new LinkedList<>();

    public Map<String, TomcatServlet> servletMapping = new HashMap<>();

    public Properties webxml = new Properties();

    public TomcatInitConfig init() {
        //加载endpoint.properties文件，同时初始话ServletMapping对象
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();

            FileInputStream fis = new FileInputStream(WEB_INF + "endpoint.properties");
            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");

                    //单实例，多线程
                    TomcatServlet obj = (TomcatServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }

                if (key.endsWith(".port")) {
                    portList.add(Integer.valueOf(webxml.getProperty(key)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


}
