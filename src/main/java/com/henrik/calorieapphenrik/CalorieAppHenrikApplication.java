package com.henrik.calorieapphenrik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CalorieAppHenrikApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalorieAppHenrikApplication.class, args);
    }

}
