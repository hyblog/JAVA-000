package java0.nio02.netty.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ipipman on 2020/10/27.
 *
 * @version V1.0
 * @Package java0.nio02.netty.rpc.protocol
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/27 3:14 下午
 */
@Data
public class InvokerProtocol implements Serializable {

    //自定义传输协议

    private String className; //类名
    private String methodName; //函数名称
    private Class<?>[] parames; //参数类型
    private Object[] values; //参数列表

}
