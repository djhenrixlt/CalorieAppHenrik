package lt.henrix.caloriesapp.user.controller;

import lombok.AllArgsConstructor;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.model.User;
import lt.henrix.caloriesapp.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/login")
    public UserDto user(@AuthenticationPrincipal User user) {
        return new UserDto(user);
    }

    @GetMapping("/api/users/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/api/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/signup")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }

    @PostMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
