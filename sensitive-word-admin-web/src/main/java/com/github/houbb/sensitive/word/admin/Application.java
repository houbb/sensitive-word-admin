package com.github.houbb.sensitive.word.admin;

import com.github.houbb.auto.log.spring.annotation.EnableAutoLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAutoLog
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}