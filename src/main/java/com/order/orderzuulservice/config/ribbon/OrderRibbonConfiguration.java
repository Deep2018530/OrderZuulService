package com.order.orderzuulservice.config.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.order.orderzuulservice.annotation.IgnoreComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by zhangdingping at 2019/11/8
 */
@Configuration
@IgnoreComponentScan
public class OrderRibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
