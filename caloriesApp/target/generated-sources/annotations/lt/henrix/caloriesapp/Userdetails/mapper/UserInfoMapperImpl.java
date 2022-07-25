package lt.henrix.caloriesapp.Userdetails.mapper;

import javax.annotation.processing.Generated;
import lt.henrix.caloriesapp.Userdetails.dto.UserInfoDto;
import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import lt.henrix.caloriesapp.user.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-24T18:18:46+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserInfoMapperImpl implements UserInfoMapper {

    @Override
    public UserInfo mapToDto(UserInfoDto userInfoDto) {
        if ( userInfoDto == null ) {
            return null;
        }

        long id = 0L;
        String gender = null;
        Double age = null;
        Double weight = null;
        Double height = null;
        String activityLevel = null;
        String plan = null;

        id = userInfoDto.getId();
        gender = userInfoDto.getGender();
        age = userInfoDto.getAge();
        weight = userInfoDto.getWeight();
        height = userInfoDto.getHeight();
        activityLevel = userInfoDto.getActivityLevel();
        plan = userInfoDto.getPlan();

        User user = null;

        UserInfo userInfo = new UserInfo( id, gender, age, weight, height, activityLevel, plan, user );

        return userInfo;
    }

    @Override
    public UserInfoDto mapToDto(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setId( userInfo.getId() );
        userInfoDto.setGender( userInfo.getGender() );
        userInfoDto.setAge( userInfo.getAge() );
        userInfoDto.setWeight( userInfo.getWeight() );
        userInfoDto.setHeight( userInfo.getHeight() );
        userInfoDto.setActivityLevel( userInfo.getActivityLevel() );
        userInfoDto.setPlan( userInfo.getPlan() );

        return userInfoDto;
    }
}
