package com.ipman.mysql.concurrentdml;

import com.ipman.mysql.concurrentdml.service.IOrderService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.concurrentdml
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 12:02 下午
 */
@SpringBootTest
public class TestMultipleAdd {

    @Autowired
    private IOrderService iOrderService;

    @Test
    @SneakyThrows
    public void run() {
        long beginMs = System.currentTimeMillis();
        iOrderService.multipleAdd();
        long nowMs = System.currentTimeMillis();
        System.out.println(nowMs - beginMs);
    }
}
