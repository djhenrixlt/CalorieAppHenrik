package com.henrik.calorieapphenrik.Person.mapper;

import com.henrik.calorieapphenrik.Person.entity.Calories;
import com.henrik.calorieapphenrik.Person.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper PERSON_MAPPER = Mappers.getMapper(PersonMapper.class);


    PersonDto mapDto(Calories calories);

    Calories mapModel(PersonDto personDto);

    @Mapping(target = "myFoodList", ignore = true)
    PersonDto mapDtoForSavePerson(Calories calories);
    @Mapping(target = "myFoodList", ignore = true)
    Calories mapModelSavePerson(PersonDto personDto);


//    @Mapping(target = "myFoodList", ignore = true)
    Calories mapForUpdate(PersonDto personDto, @MappingTarget Calories calories);

//    @Mapping(target = "username", ignore = true)
//    @Mapping(target = "fullName", ignore = true)
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "gender", ignore = true)
//    @Mapping(target = "age", ignore = true)
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "weight", ignore = true)
//    @Mapping(target = "height", ignore = true)
//    @Mapping(target = "activityLevel", ignore = true)
//    @Mapping(target = "goalCalories", ignore = true)
//    Calories mapModeFodMyList(PersonDto personDto);
}

