package lt.henrix.caloriesapp.Userdetails.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.henrix.caloriesapp.Userdetails.dto.UserInfoDto;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.service.UserInfoService;
import lt.henrix.caloriesapp.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/info")
public class UserInfoController {

//    private final UserInfoService userInfoService;
//    private final UserService userService;
//
//    @GetMapping("/{id}")
//    public UserDto getUser(@PathVariable long id) {
//        return userService.getUserByIdFood(id);
//    }


}
