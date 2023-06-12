package org.jxx.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * description: UserServiceImpl
 * date: 2023/6/12
 * author: javaear
 */
@Slf4j
@DubboService(timeout = 10000)
public class UserServiceImpl implements UserService {

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
    public void debit(String userId, int money) {
        jdbcTemplate.update("update user_tbl set count = count - ? where id = ?",
                new Object[]{money, userId});
        log.info("Storage Service End ... ");
    }
}
