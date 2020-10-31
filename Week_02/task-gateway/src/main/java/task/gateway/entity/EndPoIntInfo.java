package task.gateway.entity;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.entity
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 5:52 下午
 */
public class EndPoIntInfo {

    private String ip;

    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "EndPoIntInfo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
