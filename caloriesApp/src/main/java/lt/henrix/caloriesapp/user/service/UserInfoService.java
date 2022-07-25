package lt.henrix.caloriesapp.user.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lt.henrix.caloriesapp.UserGoals.Utils.Counter;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.entity.User;
import lt.henrix.caloriesapp.user.exception.EntityNotFoundException;
import lt.henrix.caloriesapp.user.mapper.UserMapperImpl;
import lt.henrix.caloriesapp.user.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoService {

    private UserRepo userRepo;
    private UserMapperImpl userMapperImpl;

    private Counter counter;

    public UserDto getUserByIdFood(long id) {
        Optional<User> user = userRepo.findWithFoodById(id);
        return userMapperImpl.convertUserToDTO(user.get());
    }


    private User getById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
