package com.order.orderzuulservice.config.ribbon;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * created by zhangdingping at 2019/11/8
 */
@Configuration
@RibbonClient(name = "order", configuration = OrderRibbonConfiguration.class)
public class RibbonConfiguration {
}
