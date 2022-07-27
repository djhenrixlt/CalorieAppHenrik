package lt.henrix.caloriesapp.user.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lt.henrix.caloriesapp.UserGoals.Utils.Counter;
import lt.henrix.caloriesapp.UserGoals.Utils.GoalException;
import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.Repository.FoodRepo;
import lt.henrix.caloriesapp.food.dto.FoodDto;
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
import java.util.Set;
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

//    public List<User> getUserByIdFood(long id) {
//
//        return userRepo.getAllFoodsById(id);
//    }

    public UserDto saveUser( UserDto userDto){

        UserDto userDto1 = getUserById(userDto.getId());
        userDto1.getFoods()
                .addAll(userDto.getFoods()
                        .stream()
                        .map(f -> {
                            Food food = foodRepo.getById(f.getId());
                            food.getUserFoods().add(userMapperImpl.convertUserDtoToUserEntity2(userDto1));
                            return food;
                        }).map(FoodMapper.FOOD_MAPPER::mapDto)
                        .toList());
        User  save = userMapperImpl.convertUserDtoToUserEntity2(userDto1);
//        BeanUtils.copyProperties(userDto1, save);
        userRepo.save(save);
        return userDto1;
    }
    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> userMapperImpl.convertUserToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto userDto) {
        Long id = userDto.getId();
        if (id == null) {
            throw new EntityNotFoundException(id);
        }
        User user = getById(id);
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
    public @NonNull UserDto addToMyList(User user, Long foodId) {
        Optional<Food> food = foodRepo.findById(foodId);

//        setUserGoals(caloriesDto, caloriesDto.getCaloriesConsumed() + foodDto.get().getCalories());
//        Calories calories = CaloriesMapper.CALORIES_MAPPER.mapModel(caloriesDto);
//        Calories save = CaloriesMapper.CALORIES_MAPPER.mapForUpdate(caloriesDto, calories);
//        caloriesRepo.save(save);
        user.addFood(food.get());
        User save = userRepo.save(user);

        UserDto userDto = userMapperImpl.convertUserToDTO(save);
        return userDto;
    }

    private User getById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
