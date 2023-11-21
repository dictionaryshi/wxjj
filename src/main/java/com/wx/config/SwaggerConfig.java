package com.wx.config;

import com.scy.core.SystemUtil;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.web.util.SwaggerUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

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

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                mappings.removeIf(mapping -> Objects.nonNull(mapping.getPatternParser()));
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}
