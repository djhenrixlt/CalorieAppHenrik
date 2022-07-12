package com.henrik.calorieapphenrik.user.mapper;

import com.henrik.calorieapphenrik.user.dto.UserDto;
import com.henrik.calorieapphenrik.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER= Mappers.getMapper(UserMapper.class);


    @Mapping(target = "roles", ignore = true)
    UserDto mapDto(User user);

    @Mapping(target = "roles", ignore = true)
    User mapModel(UserDto userDto);


        @Mapping(target = "roles", ignore = true)
    User mapForUpdate(UserDto personDto, @MappingTarget User person);


}
