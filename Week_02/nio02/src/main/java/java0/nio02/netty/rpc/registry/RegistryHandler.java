package java0.nio02.netty.rpc.registry;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java0.nio02.netty.rpc.protocol.InvokerProtocol;

import javax.print.DocFlavor;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ipipman on 2020/10/27.
 *
 * @version V1.0
 * @Package java0.nio02.netty.rpc.registry
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/27 3:36 下午
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

    // 用保存所有的服务
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>();

    // 保存所有相关的服务类
    private List<String> classNames = new ArrayList<>();

    public RegistryHandler() {
        //完成递归扫描 java0.nio02.netty.rpc.provider
        scannerClass("java0.nio02.netty.rpc.provider");
        //注册
        doRegister();
    }

    //方法处理
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;

        //当客户端建立链接时，需要从自定义协议中获取信息，拿到具体的服务和实参
        //使用反射调用
        if (registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
            //从request对象获取请求的类、方法、参数信息
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParames());
            //反射调用方法
            result = method.invoke(clazz, request.getValues());
        }

        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    //递归扫描
    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            //如果是一个文件夹，继续递归
            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    //完成注册，反射
    private void doRegister() {
        if (classNames.size() == 0) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                System.out.println(i);
                registryMap.put(i.getName(), clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
