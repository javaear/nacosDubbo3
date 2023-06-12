package org.jxx.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * description: OrderApp
 * date: 2023/6/12
 * author: javaear
 */
@SpringBootApplication
@EnableDubbo
public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Component
    static class Init implements CommandLineRunner {
        private JdbcTemplate jdbcTemplate;

        @Autowired
        public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public void run(String... args) throws Exception {
            //jdbcTemplate.update("delete from account_tbl where user_id = 'U100001'");
            //jdbcTemplate.update("insert into account_tbl(user_id, money) values ('U100001', 999)");
        }
    }

}