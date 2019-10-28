package com.order.orderzuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class OrderZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderZuulServiceApplication.class, args);
    }

}
