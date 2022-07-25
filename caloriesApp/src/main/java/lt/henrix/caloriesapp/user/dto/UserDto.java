package lt.henrix.caloriesapp.user.dto;

import lombok.*;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import lt.henrix.caloriesapp.user.entity.Role;
import lt.henrix.caloriesapp.user.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    @Size(min = 3, max = 20)
    private String lastname;

    @Size(min = 3, max = 20)
    private String name;

    private String email;
    private String phone;

    private Set<String> roles = new HashSet<>();

    private String gender; // enum
    private Double age;
    private Double weight;
    private Double height;
    private String activityLevel; //enum
    private String plan; //enum


    private Integer goalCalories;
    private Integer caloriesLeft;
    private Integer caloriesConsumed;
    private Integer goalProtein;
    private Integer goalCarbs;
    private Integer goalFats;

    private Set<FoodDto> foods = new HashSet<>();

    //response after successful login
    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

    }
}
