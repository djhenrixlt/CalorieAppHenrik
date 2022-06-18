package com.henrik.calorieapphenrik.food.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//
@Configuration
//@EnableWebSecurity
public class WebConfig    implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("myFoodList");
        registry.addViewController("/login").setViewName("login");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//
//                .authorizeRequests()
//                .antMatchers("/persons", "/foods").access("hasRole('ROLE_USER')")
//                .antMatchers("/", "/**").access("permitAll")
//
//                .and()
//                .formLogin()
//                .loginPage("/foods/login")
//                .defaultSuccessUrl("/persons/main", true)
//
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//
//                .and()
//                .csrf()
//                .ignoringAntMatchers("/h2/**")
//
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//        ;
//
//    }


}
