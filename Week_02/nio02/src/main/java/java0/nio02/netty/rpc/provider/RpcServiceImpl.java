package java0.nio02.netty.rpc.provider;

import java0.nio02.netty.rpc.api.IRpcService;

/**
 * Created by ipipman on 2020/10/27.
 *
 * @version V1.0
 * @Package java0.nio02.netty.rpc.provider
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/27 3:18 下午
 */
public class RpcServiceImpl implements IRpcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
