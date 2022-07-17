package lt.henrix.caloriesapp.user.service;

import lombok.AllArgsConstructor;
import lt.henrix.caloriesapp.user.dto.UserDto;
import lt.henrix.caloriesapp.user.exception.EntityNotFoundException;
import lt.henrix.caloriesapp.user.mapper.UserMapper;
import lt.henrix.caloriesapp.user.model.User;
import lt.henrix.caloriesapp.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserDto createUser(UserDto userDto){
        User user = userMapper.convertUserDtoToUserEntity(userDto);
        User saveUser = userRepository.save(user);
        userDto.setId(saveUser.getId());
        return userDto;
    }

    public UserDto getUserById(long id) {
        User user = getById(id);
        return userMapper.convertUserToDTO(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.convertUserToDTO(user))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto userDto) {
        Long id = userDto.getId();
        if(id == null){
            throw new EntityNotFoundException(id);
        }
        User user = getById(id);
        userMapper.convertUserDtoToUserEntity(userDto);
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
        return userDto;
    }

    public void deleteUser(long id) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findWithRolesByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    private User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
