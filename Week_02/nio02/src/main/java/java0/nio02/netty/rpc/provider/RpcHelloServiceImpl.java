package java0.nio02.netty.rpc.provider;

import java0.nio02.netty.rpc.api.IRpcHelloService;

/**
 * Created by ipipman on 2020/10/27.
 *
 * @version V1.0
 * @Package java0.nio02.netty.rpc.provider
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/27 3:17 下午
 */
public class RpcHelloServiceImpl implements IRpcHelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name + " !";
    }
}
