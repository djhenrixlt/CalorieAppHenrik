package lt.henrix.caloriesapp.Userdetails.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lt.henrix.caloriesapp.Userdetails.dto.UserInfoDto;
import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import lt.henrix.caloriesapp.Userdetails.mapper.UserInfoMapper;
import lt.henrix.caloriesapp.Userdetails.repo.UserInfoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService {

    private final UserInfoRepo userInfoRepo;

    public UserInfoDto getUserByID(Long id){
        Optional<UserInfo> userInfo = userInfoRepo.findById(id);
        return UserInfoMapper.USER_INFO_MAPPER.mapToDto(userInfo.get());
    }

    public List<UserInfoDto> getAll(){
        return userInfoRepo.findAll()
                .stream()
                .map(UserInfoMapper.USER_INFO_MAPPER::mapToDto)
                .toList();
    }
}
