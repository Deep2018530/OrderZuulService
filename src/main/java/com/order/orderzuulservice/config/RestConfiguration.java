package com.order.orderzuulservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * created by zhangdingping at 2019/10/30
 */
@Configuration
public class RestConfiguration {

    @Autowired
    RestTemplateBuilder builder;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
}
