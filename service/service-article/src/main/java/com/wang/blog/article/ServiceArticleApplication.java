package com.wang.blog.article;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wang"})
public class ServiceArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceArticleApplication.class, args);
    }
}
