package lt.henrix.caloriesapp.user.service;

import lombok.AllArgsConstructor;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.exception.EntityNotFoundException;
import lt.henrix.caloriesapp.user.mapper.UserMapperImpl;
import lt.henrix.caloriesapp.user.entity.User;
import lt.henrix.caloriesapp.user.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;
    private UserMapperImpl userMapperImpl;

    public UserDto createUser(UserDto userDto){
        User user = userMapperImpl.convertUserDtoToUserEntity(userDto);
        User saveUser = userRepo.save(user);
        userDto.setId(saveUser.getId());
        return userDto;
    }

    public UserDto getUserById(long id) {
        User user = getById(id);
        return userMapperImpl.convertUserToDTO(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> userMapperImpl.convertUserToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto userDto) {
        Long id = userDto.getId();
        if(id == null){
            throw new EntityNotFoundException(id);
        }
        User user = getById(id);
        userMapperImpl.convertUserDtoToUserEntity(userDto);
        BeanUtils.copyProperties(userDto, user);
        userRepo.save(user);
        return userDto;
    }

    public void deleteUser(long id) {
        if(!userRepo.existsById(id)){
            throw new EntityNotFoundException(id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findWithRolesByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    private User getById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
