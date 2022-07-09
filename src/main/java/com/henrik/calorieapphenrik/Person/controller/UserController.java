package com.henrik.calorieapphenrik.Person.controller;

import com.henrik.calorieapphenrik.Person.dto.UserDto;
import com.henrik.calorieapphenrik.Person.entity.User;
import com.henrik.calorieapphenrik.Person.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto user(@AuthenticationPrincipal User user) {
        return new UserDto(user);
    }
}
