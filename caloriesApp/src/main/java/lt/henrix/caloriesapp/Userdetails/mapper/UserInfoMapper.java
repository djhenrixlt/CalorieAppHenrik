package lt.henrix.caloriesapp.Userdetails.mapper;

import lt.henrix.caloriesapp.Userdetails.dto.UserInfoDto;
import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;

@Mapper
public interface UserInfoMapper {

    UserInfoMapper USER_INFO_MAPPER = Mappers.getMapper(UserInfoMapper.class);

    UserInfo mapToDto(UserInfoDto userInfoDto);

    UserInfoDto mapToDto(UserInfo userInfo);
}
