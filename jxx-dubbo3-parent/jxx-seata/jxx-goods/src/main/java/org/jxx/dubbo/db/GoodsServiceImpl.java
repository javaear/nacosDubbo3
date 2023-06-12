package org.jxx.dubbo.db;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.jxx.dubbo.GoodsService;
import org.jxx.dubbo.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * description: GoodsServiceImpl
 * date: 2023/6/12
 * author: javaear
 */
@Slf4j
@DubboService(timeout = 10000)
public class GoodsServiceImpl implements GoodsService {


    private JdbcTemplate jdbcTemplate;

    /**
     * Set jdbc template.
     *
     * @param jdbcTemplate the jdbc template
     */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deduct(String commodityCode, int count) {
        log.info("Storage Service Begin ... xid: " + RootContext.getXID());
        log.info("Deducting inventory SQL: update storage_tbl set count = count - {} where commodity_code = {}",
                count, commodityCode);

        jdbcTemplate.update("update goods_tbl set count = count - ? where id = ?",
                new Object[]{count, commodityCode});
        log.info("Storage Service End ... ");

    }
}
