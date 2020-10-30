package java0.nio02.netty.tomcat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import java0.nio02.netty.tomcat.http.GPServlet;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ipipman on 2020/10/26.
 *
 * @version V1.0
 * @Package java0.nio02.netty.tomcat
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/26 3:09 下午
 */
public class GPTomcat {

    //TODO 三个阶段初始化阶段，服务器就绪阶段，接受请求阶段

    private int port = 8088;

    private ServerSocket server;
    private Map<String, GPServlet> servletMapping = new HashMap<String, GPServlet>();

    private Properties webxml = new Properties();


    // TODO 初始化阶段，完成web.properties解析
    private void init() {
        //加载web.properties文件，同时初始话ServletMapping对象
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();

            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");

                    //单实例，多线程
                    GPServlet obj = (GPServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // TODO 服务就绪阶段，完成ServerSokect的准备工作（Netty实现版本）
    private void startByNetty() {
        // 初始化
        init();

        //Netty封装了NIO的Reactor模型，Boss，Worker
        //Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建对象
            ServerBootstrap server = new ServerBootstrap();


            // 配置参数
            // 链路式编程
            server.group(bossGroup, workerGroup)
                    // 主线程处理类（看到这样的写法，底层就是用反射）
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类，Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            // 无锁化串行编程
                            // Netty对HTTP的封装，对顺序有要求
                            // HttpResponseEncoder 编码器
                            // 责任链模式，双向链表Inbound OutBound
                            client.pipeline().addLast(new HttpResponseEncoder());

                            // HttpRequestEncoder 解码器
                            client.pipeline().addLast(new HttpRequestDecoder());

                            // 业务处理逻辑
                            client.pipeline().addLast(new GPTomcatHandler(servletMapping));
                        }
                    })
                    // 针对主线程的配置 分配线程最大数量 128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 针对子线程的配置 保持长链接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务器
            ChannelFuture f = server.bind(port).sync();
            System.out.println("GPTomcat 已启动，监听端口：" + port);

            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        new GPTomcat().startByNetty();
    }


}




//    // TODO 接受请求阶段，完成每次请求处理
//    private void process(Socket client) throws Exception {
//        InputStream is = client.getInputStream();
//        OutputStream os = client.getOutputStream();
//
//        GPRequest request = new GPRequest(is);
//        GPResponse response = new GPResponse(os);
//
//        // 从协议内容中获取URl，把相应的Servlet用反射进行实例话
//        String url = request.getUrl();
//
//        if (servletMapping.containsKey(url)) {
//            //调用实例话对象的service()，只想具体的doGet/doPost逻辑
//            servletMapping.get(url).service(request, response);
//        } else {
//            response.write("404 - Not Found");
//        }
//
//        os.flush();
//        os.close();
//
//        is.close();
//        client.close();
//    }
//
//    // TODO 服务就绪阶段，完成ServerSokect的准备工作
//    public void start() {
//        // 加载配置文件，初始化ServletMapping
//        init();
//        try {
//            server = new ServerSocket(port);
//            System.out.println("GPTomcat 已启动，监听的端口是：" + this.port);
//
//            //等待死循环
//            while (true) {
//                //这是一个阻塞的方法
//                Socket socket = server.accept();
//
//                //HTTP请求，发送的数据就是字符串
//                process(socket);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
