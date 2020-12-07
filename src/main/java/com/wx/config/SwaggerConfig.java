package com.wx.config;

import com.scy.core.SystemUtil;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.web.util.SwaggerUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/21.
 */
@Profile(value = {ApplicationContextUtil.ENV_DEV, ApplicationContextUtil.ENV_STAG})
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerDocket() {
        return SwaggerUtil.getDocket("温馨家具",
                "接口API" + SystemUtil.SYSTEM_LINE_BREAK
                        + "责任人:史春阳, 联系方式:903031015@qq.com",
                "1.0", "com.wx.controller.api");
    }
}
