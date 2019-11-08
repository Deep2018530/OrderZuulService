package com.order.orderzuulservice;

import com.order.orderzuulservice.annotation.IgnoreComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableZuulProxy
@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(classes = IgnoreComponentScan.class)})
public class OrderZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderZuulServiceApplication.class, args);
    }

}
