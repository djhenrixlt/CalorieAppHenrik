package com.henrik.calorieapphenrik.food.service;

import com.henrik.calorieapphenrik.food.Entity.Person;
import com.henrik.calorieapphenrik.food.Repository.PersonRepo;
import com.henrik.calorieapphenrik.food.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {

    private final PersonRepo personRepo;
    private  PersonMapper personMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user name not found" + username));
    }
}
