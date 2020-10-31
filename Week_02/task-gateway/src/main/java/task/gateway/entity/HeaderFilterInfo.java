package task.gateway.entity;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.entity
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 5:54 下午
 */
public class HeaderFilterInfo {
    private String serverName;

    private String serverVersion;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    @Override
    public String toString() {
        return "HeaderFilterInfo{" +
                "serverName='" + serverName + '\'' +
                ", serverVersion='" + serverVersion + '\'' +
                '}';
    }
}
