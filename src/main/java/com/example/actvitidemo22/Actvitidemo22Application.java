package com.example.actvitidemo22;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Actvitidemo22Application {

    public static void main(String[] args) {
        SpringApplication.run(Actvitidemo22Application.class, args);
    }

}
