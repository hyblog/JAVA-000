package java0.nio02.netty.rpc.api;

/**
 * Created by ipipman on 2020/10/27.
 *
 * @version V1.0
 * @Package java0.nio02.netty.rpc.api
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/27 3:11 下午
 */
public interface IRpcService {

    public int add(int a, int b );

    public int sub(int a, int b);

    public int mult(int a, int b);

    public int div(int a, int b);
}
