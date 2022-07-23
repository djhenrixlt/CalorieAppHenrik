package lt.henrix.caloriesapp.user.mapper;

import lombok.AllArgsConstructor;
import lt.henrix.caloriesapp.UserGoals.entity.Goal;
import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import lt.henrix.caloriesapp.user.dto.RoleDto;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.entity.Role;
import lt.henrix.caloriesapp.user.entity.User;
import lt.henrix.caloriesapp.user.repository.RoleRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapperImpl {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;


    public UserDto convertUserToDTO(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setRoles(user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet()));
        userDto.setGender(user.getUserInfo().getGender());
        userDto.setAge(user.getUserInfo().getAge());
        userDto.setHeight(user.getUserInfo().getHeight());
        userDto.setActivityLevel(user.getUserInfo().getActivityLevel());
        userDto.setPlan(user.getUserInfo().getPlan());
        userDto.setCalories(user.getGoal().getGoalCalories());
        userDto.setLeft(user.getGoal().getCaloriesLeft());
        userDto.setConsumed(user.getGoal().getCaloriesConsumed());
        userDto.setProtein(user.getGoal().getGoalProtein());
        userDto.setCarbs(user.getGoal().getGoalCarbs());
        userDto.setFats(user.getGoal().getGoalFats());
        userDto.setFoods(user.getFoods().stream()
                .map(food -> FoodDto.builder()
                        .calories(food.getCalories())
                        .carbs(food.getCarbs())
                        .fat(food.getFat())
                        .name(food.getName())
                        .protein(food.getProtein())
                        .build()).collect(Collectors.toSet()));
        return userDto;
    }


    public User convertUserDtoToUserEntity(UserDto userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());

        Role role = roleRepo.getById(2L);
        user.addRole(role);

        UserInfo userInfo = UserInfo.builder()
                .activityLevel(userDTO.getActivityLevel())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .weight(userDTO.getWeight())
                .height(userDTO.getHeight())
                .plan(userDTO.getPlan())
                .build();
        user.addUserInfo(userInfo);

        Goal userGoals = Goal.builder()
                .goalCalories(userDTO.getCalories())
                .goalCarbs(userDTO.getCarbs())
                .caloriesConsumed(userDTO.getConsumed())
                .goalFats(userDTO.getFats())
                .caloriesLeft(userDTO.getLeft())
                .build();
        user.addGoal(userGoals);

        user.setFoods(userDTO.getFoods().stream()
                .map(food -> Food.builder()
                        .name(food.getName())
                        .calories(food.getCalories())
                        .protein(food.getProtein())
                        .carbs(food.getCarbs())
                        .fat(food.getFat())
                        .fiber(food.getFiber())
                        .build()).collect(Collectors.toSet()));
        return user;
    }

    private Set<RoleDto> createListRolesDto(Set<Role> roles) {

        return roles.stream()
                .map(role ->

                        RoleDto.builder()
                                .roleName(role.getRoleName())
                                .build()
                )
                .collect(Collectors.toSet());
    }


}
