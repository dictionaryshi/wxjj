package com.wx.config;

import com.scy.core.snowflake.Snowflake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : shichunyang
 * Date    : 2021/2/2
 * Time    : 11:51 上午
 * ---------------------------------------
 * Desc    : SnowflakeConfig
 */
@Configuration
public class SnowflakeConfig {

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(1);
    }
}
