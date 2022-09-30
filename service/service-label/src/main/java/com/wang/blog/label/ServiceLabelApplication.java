package com.wang.blog.label;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wang"})
public class ServiceLabelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceLabelApplication.class, args);
    }
}
