package com.ipman.work05sb2.cache;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.cache
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 8:05 下午
 */
@RestController
public class MyCacheController {

    /**
     * 未命中缓存时触触发
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/cache/get")
    @MyCacheAnn(expireSecond = 6)
    public Object toItem() throws InterruptedException {
        //假设一堆逻辑用了6s
        Thread.sleep(500);
        System.out.println("缓存过期了");
        return "ipman";
    }
}
