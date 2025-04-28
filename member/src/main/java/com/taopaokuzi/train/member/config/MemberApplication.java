package com.taopaokuzi.train.member.config;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.taopaokuzi")
public class MemberApplication {
    public static void main(String[] args){
        SpringApplication.run(MemberApplication.class,args);
    }
}
