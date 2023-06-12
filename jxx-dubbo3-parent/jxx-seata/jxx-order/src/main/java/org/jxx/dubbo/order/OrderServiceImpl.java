package org.jxx.dubbo.order;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jxx.dubbo.GoodsService;
import org.jxx.dubbo.OrderService;
import org.jxx.dubbo.UserService;
import org.springframework.stereotype.Service;

/**
 * description: OrderService
 * date: 2023/6/12
 * author: javaear
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @DubboReference
    private GoodsService goodsService;

    @DubboReference
    private UserService userService;


    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-demo-tx")
    public void purchase(String userId, String commodityCode, int orderCount, Boolean needRollback) {
        log.info("===purchase=========================");
        goodsService.deduct(commodityCode, orderCount);
        userService.debit(userId, orderCount);
        if (needRollback) {
            throw new RuntimeException("xxx");
        }
    }
}
