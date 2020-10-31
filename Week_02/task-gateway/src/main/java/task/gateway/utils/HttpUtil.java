package task.gateway.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by ipipman on 2020/10/31.
 *
 * @version V1.0
 * @Package task.gateway.utils
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/31 10:23 下午
 */
public class HttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    //to 10s
    private final static int CONNECT_TIMEOUT = 10000;
    private final static int READ_TIMEOUT = 10000;
    private final static int WRITE_TIMEOUT = 10000;

    public static String doHttpGet(String url, String userAgent) {
        try {
            Request request = new Request.Builder()
                    .url(url).header("User-Agent", userAgent)
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .build();
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            }
        } catch (Exception e) {
            logger.error("httpGet err=" + e.getMessage());
        }
        return null;
    }


    /**
     * HTTP Method Post
     *
     * @param url
     * @param body
     * @return
     */
    public static String httpPost(String url, String body) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
            //创建一个请求对象
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            //发送请求获取响应
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            }
        } catch (Exception e) {
            logger.error("httpPost err=" + e.getMessage());
        }
        return null;
    }
}
