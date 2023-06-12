package org.jxx.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: OrderViewController
 * date: 2023/6/12
 * author: javaear
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderViewController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/w")
    public String join( @RequestParam("w") String w){

        orderService.purchase("1","1",1,false);
        log.info("=============result:======");
        return "Y";
    }
}
