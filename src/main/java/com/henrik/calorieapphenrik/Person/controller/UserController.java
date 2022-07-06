package com.henrik.calorieapphenrik.Person.controller;

import com.henrik.calorieapphenrik.Person.dto.UserDto;
import com.henrik.calorieapphenrik.Person.entity.User;
import com.henrik.calorieapphenrik.Person.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public UserDto user(@AuthenticationPrincipal User user) {
        return new UserDto(user);
    }
}
