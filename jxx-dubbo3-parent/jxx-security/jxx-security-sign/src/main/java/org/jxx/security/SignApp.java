package org.jxx.security;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * description: SignApp
 * date: 2023/6/17
 * author: javaear
 */
@SpringBootApplication
@EnableDubbo
public class SignApp {

    public static void main(String[] args) {
        SpringApplication.run(SignApp.class, args);
    }

}