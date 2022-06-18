package com.henrik.calorieapphenrik.food.mapper;

import com.henrik.calorieapphenrik.food.Entity.Person;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper PERSON_MAPPER = Mappers.getMapper(PersonMapper.class);

    //    @Mapping(target = "energyUnitMax", ignore = true)
    PersonDto mapDto(Person person);

    Person mapModel(PersonDto personDto);

    @Mapping(target = "myFoodList", ignore = true)
    PersonDto mapDtoForSavePerson(Person person);
    @Mapping(target = "myFoodList", ignore = true)
    Person mapModelSavePerson(PersonDto personDto);


//    @Mapping(target = "myFoodList", ignore = true)
    Person mapForUpdate(PersonDto personDto, @MappingTarget Person person);

    @Mapping(target = "username", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "weight", ignore = true)
    @Mapping(target = "height", ignore = true)
    @Mapping(target = "activityLevel", ignore = true)
    @Mapping(target = "goalCalories", ignore = true)
    Person mapModeFodMyList(PersonDto personDto);
}

