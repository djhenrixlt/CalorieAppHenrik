package lt.henrix.caloriesapp.user.service;

import lombok.AllArgsConstructor;
import lt.henrix.caloriesapp.user.Utils.Counter;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.entity.User;
import lt.henrix.caloriesapp.user.exception.GoalException;
import lt.henrix.caloriesapp.user.mapper.UserMapperImpl;
import lt.henrix.caloriesapp.user.repository.RoleRepo;
import lt.henrix.caloriesapp.user.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class UserServiceTest {

    @InjectMocks
    private  UserService userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepo roleRepo;

    @Mock
    private  UserMapperImpl userMapper = new UserMapperImpl(passwordEncoder,roleRepo);

    @Mock
    private Counter counter;

    @Test
    void createUser() throws GoalException {
        UserDto userDto = getUserDTo1();
        User user = userMapper.convertUserDtoToUserEntity(userDto);
        counter.setGoals(user.getUserInfo(),user.getGoal());
        when(userRepo.save(user)).thenReturn(user);

        UserDto actual = userService.createUser(userDto);
        verify(userRepo).save(user);
        testUser(user,actual);
    }


    @Test
    void getUserById() {

    }

    @Test
    void getAllUsers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void addToFoods() {
    }

    @Test
    void deleteUserFood() {
    }
    private void testUser(User user, UserDto actual) {
        assertEquals(user.getUsername(), actual.getUsername());
        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getUserInfo().getActivityLevel(),actual.getActivityLevel());
        assertEquals(user.getPassword(), actual.getPassword());
        assertEquals(user.getUserInfo().getAge(),actual.getAge());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals(user.getUserInfo().getGender(), actual.getGender());
        assertEquals(user.getUserInfo().getHeight(), actual.getHeight());
        assertEquals(user.getUserInfo().getWeight(), actual.getWeight());
        assertEquals(user.getLastname(),actual.getLastname());
        assertEquals(user.getPhone(), actual.getPhone());
        assertEquals(user.getUserInfo().getPlan(), actual.getPlan());
    }

    private UserDto getUserDTo1() {
        return UserDto.builder()
                .id(7L)
                .username("a")
                .name("a")
                .activityLevel("moderately")
                .password("password")
                .age(25.0)
                .email("2@d.com")
                .gender("man")
                .height(185.0)
                .weight(87.0)
                .lastname("a")
                .phone("87")
                .plan("same")
                .build();
    }
//    private  User getUser() {
//        return User.builder()
//                .id(7L)
//                .username("a")
//                .name("a")
//                .userInfo().
//                .password("password")
//                .age(25.0)
//                .email("2@d.com")
//                .gender("man")
//                .height(185.0)
//                .weight(87.0)
//                .lastname("a")
//                .phone("87")
//                .plan("same")
//                .build();
//    }

}
