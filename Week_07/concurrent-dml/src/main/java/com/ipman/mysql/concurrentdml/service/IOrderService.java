package com.ipman.mysql.concurrentdml.service;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.concurrentdml
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 11:34 上午
 */
public interface IOrderService {

    void multipleAdd() throws RuntimeException;
}
