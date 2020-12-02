package com.ipman.mysql.concurrentdml.service.impl;

import com.ipman.mysql.concurrentdml.dao.TOrderMapper;
import com.ipman.mysql.concurrentdml.model.TOrder;
import com.ipman.mysql.concurrentdml.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.concurrentdml.impl
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 11:34 上午
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    //总数据量
    private static final int MAX_ADD_VAL = 1000000;

    //单次写入条数
    private static final int MAX_LIST_SIZE = 500;

    @Resource
    TOrderMapper tOrderMapper;

    //批量写入数据
    @Override
    @Transactional("transactionManager")
    public void multipleAdd() throws RuntimeException {
        try {
            //准备数据
            List<TOrder> orderList = initOrderList();

            //分页数据
            if (orderList.size() <= MAX_LIST_SIZE) {
                tOrderMapper.multipleInsertByList(orderList);
            } else {
                int pages = (int) Math.ceil((double) orderList.size() / (double) MAX_LIST_SIZE);
                for (int i = 0; i < pages; i++) {
                    int fromIndex = i * MAX_LIST_SIZE;
                    int toIndex = Math.min((i + 1) * MAX_LIST_SIZE, orderList.size());
                    tOrderMapper.multipleInsertByList(orderList.subList(fromIndex, toIndex));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //准备数据
    public List<TOrder> initOrderList() {
        List<TOrder> orderList = new LinkedList<>();
        for (int i = 0; i < MAX_ADD_VAL; i++) {
            TOrder tOrder = new TOrder();
            tOrder.setOrderCode("1");
            tOrder.setUserId("1");
            tOrder.setSupplierId(1);
            tOrder.setChannelId(1);
            tOrder.setPhone("1");
            tOrder.setBuyAmount(1);
            tOrder.setPayTime(new Date());
            tOrder.setPayType((byte) 1);
            tOrder.setSkuId(1);
            tOrder.setAccount("1");
            tOrder.setSpuId(1);
            tOrder.setSpuCode("1");
            tOrder.setOrderType((byte) 1);
            tOrder.setActivityId(1);
            tOrder.setStatus((byte) 1);
            tOrder.setOrderRefundPrice(new BigDecimal("0.00"));
            tOrder.setPayAmount(new BigDecimal("0.00"));
            tOrder.setRealPay(new BigDecimal("0.00"));
            tOrder.setPayId("1");
            tOrder.setDiffPrice(new BigDecimal("0.00"));
            tOrder.setTransactionId("1");
            tOrder.setIsDel((byte) 0);
            tOrder.setPayErrorReason("1");
            tOrder.setPayContext("1");
            tOrder.setVipPrice(new BigDecimal("0.00"));
            orderList.add(tOrder);
        }
        return orderList;
    }

}
