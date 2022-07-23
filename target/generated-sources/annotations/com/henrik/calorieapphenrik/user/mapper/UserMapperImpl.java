package com.henrik.calorieapphenrik.user.mapper;

import com.henrik.calorieapphenrik.user.dto.UserDto;
import com.henrik.calorieapphenrik.user.entity.User;
import com.henrik.calorieapphenrik.user.entity.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-16T22:07:35+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setFullName( user.getFullName() );
        userDto.setEmail( user.getEmail() );
        userDto.setGender( user.getGender() );
        userDto.setAge( user.getAge() );
        userDto.setWeight( user.getWeight() );
        userDto.setHeight( user.getHeight() );
        userDto.setActivityLevel( user.getActivityLevel() );
        userDto.setPlan( user.getPlan() );
        userDto.setCalories( user.getCalories() );

        return userDto;
    }

    @Override
    public User mapModel(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.username( userDto.getUsername() );
        user.password( userDto.getPassword() );
        user.fullName( userDto.getFullName() );
        user.email( userDto.getEmail() );
        user.gender( userDto.getGender() );
        user.age( userDto.getAge() );
        user.weight( userDto.getWeight() );
        user.height( userDto.getHeight() );
        user.activityLevel( userDto.getActivityLevel() );
        user.plan( userDto.getPlan() );
        user.calories( userDto.getCalories() );

        return user.build();
    }

    @Override
    public User mapForUpdate(UserDto personDto, User person) {
        if ( personDto == null ) {
            return null;
        }

        person.setId( personDto.getId() );
        person.setCalories( personDto.getCalories() );

        return person;
    }
}
