package com.wx;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * WxjjApplication
 *
 * @author shichunyang
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        KafkaAutoConfiguration.class,
})
public class WxjjApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxjjApplication.class, args);
    }

}
