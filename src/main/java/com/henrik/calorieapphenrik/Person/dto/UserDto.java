package com.henrik.calorieapphenrik.Person.dto;

import com.henrik.calorieapphenrik.Person.entity.Calories;
import com.henrik.calorieapphenrik.Person.entity.Role;
import com.henrik.calorieapphenrik.Person.entity.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;

//    @NotBlank
    private  String username;
    @Size(min = 6, max = 255)
    private  String password;
//    @NotBlank
    private  String fullName;
//    @NotBlank
    @Email
    private  String email;
//    @NotBlank
    private String gender;
    private Double age;
//    @NotBlank
    private Double weight;
//    @NotBlank
    private Double height;
//    @NotBlank
    private String activityLevel;

//    @NotBlank
    private String plan;

    private Calories calories;


    private Set<String> roles = new HashSet<>();


    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password),
                fullName, email, gender,age,weight,height, activityLevel, plan);
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username=user.getUsername();
        this.roles=user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }
}

