package com.henrik.calorieapphenrik.food.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public UserDetailService userDetailsService2(PasswordEncoder encoder) {
//        List<UserDetails> usersList = new ArrayList<>();
//        usersList.add(new User(
//                "buzz", encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        usersList.add(new User(
//                "woody", encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(usersList);
//    }

//
//    @Bean
//    public UserDetailService userDetailService(PersonRepo userRepository){
//        return username -> {
//            Person user = userRepository.findByUsername(username);
//            if (user != null) return  user;
//            throw new UsernameNotFoundException("User '" + username + "' not found");
//        };
//    }


    @Override
protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/foods", "/persons", "/myList").authenticated()
//                .antMatchers("/persons/", "/**").aut

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/persons/main", true)

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                .and()
                .csrf()
                .ignoringAntMatchers("/h2/**")

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
        ;

    }

    @Override
public void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }
}
