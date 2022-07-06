package com.henrik.calorieapphenrik.Person.service;

import com.henrik.calorieapphenrik.Person.Repository.PersonRepo;
import com.henrik.calorieapphenrik.Person.Repository.UserRepo;
import com.henrik.calorieapphenrik.Person.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {

   private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findWithRolesByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user name not found" + username));
    }
}
