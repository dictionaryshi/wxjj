package com.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * WxjjApplication
 *
 * @author shichunyang
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WxjjApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxjjApplication.class, args);
    }

}
