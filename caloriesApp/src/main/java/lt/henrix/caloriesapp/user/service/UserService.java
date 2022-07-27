package lt.henrix.caloriesapp.user.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lt.henrix.caloriesapp.UserGoals.Utils.Counter;
import lt.henrix.caloriesapp.UserGoals.Utils.GoalException;
import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.Exception.FoodException;
import lt.henrix.caloriesapp.food.Repository.FoodRepo;
import lt.henrix.caloriesapp.food.mapper.FoodMapper;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.entity.User;
import lt.henrix.caloriesapp.user.exception.EntityNotFoundException;
import lt.henrix.caloriesapp.user.mapper.UserMapperImpl;
import lt.henrix.caloriesapp.user.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;
    private UserMapperImpl userMapperImpl;
    private FoodRepo foodRepo;



    private Counter counter;

    public UserDto createUser(UserDto userDto) throws GoalException {
        User user = userMapperImpl.convertUserDtoToUserEntity(userDto);
        counter.setGoals(user.getUserInfo(), user.getGoal());
        User saveUser = userRepo.save(user);
        userDto.setId(saveUser.getId());
        return userDto;
    }

    public UserDto getUserById(long id) {
        User user = getById(id);
        return userMapperImpl.convertUserToDTOFood(user);
    }



    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> userMapperImpl.convertUserToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto userDto) throws GoalException {
        Long id = userDto.getId();
        if (id == null) {
            throw new EntityNotFoundException(id);
        }

        User user = getById(id);
        counter.setGoals(user.getUserInfo(), user.getGoal());
        userMapperImpl.convertUserDtoToUserEntity(userDto);
        BeanUtils.copyProperties(userDto, user);
        userRepo.save(user);
        return userDto;
    }

    public void deleteUser(long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findWithRolesByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Transactional
    public @NonNull UserDto addToFoods(User user, Long foodId) {
        Optional<Food> food = foodRepo.findById(foodId);
        setUserGoals(user, user.getGoal().getCaloriesConsumed() + food.get().getCalories());
        user.addFood(food.get());
        User save = userRepo.save(user);
        UserDto userDto = userMapperImpl.convertUserToDTO(save);
        return userDto;
    }

        public void deleteUserFood(Long foodId, Long personID) {
        User user = userRepo.getById(personID);
        Food food = foodRepo.getById(foodId);
        UserDto userDto = userMapperImpl.convertUserToDTO(user);
        Optional<List<Food>> foods = Optional.ofNullable(foodRepo.findAllWithUserFoodsByUserId(user.getId()));
//        if (!foods.get().contains(foodId)) {
//            throw new FoodException("food do not exist");
//        }
        setUserGoals(user, user.getGoal().getCaloriesConsumed() - food.getCalories());
//        Calories calories = CaloriesMapper.CALORIES_MAPPER.mapModel(caloriesDto);
////        Calories save = CaloriesMapper.CALORIES_MAPPER.mapForUpdate(caloriesDto, calories);
////        caloriesRepo.save(save);
        user.deleteFood(food);
        userRepo.save(user);
    }

    private User getById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

        private void setUserGoals(User user, int foodCalories) {
        user.getGoal().setCaloriesConsumed(foodCalories);
        user.getGoal().setCaloriesLeft(user.getGoal().getGoalCalories() - user.getGoal().getCaloriesConsumed());
    }
}
