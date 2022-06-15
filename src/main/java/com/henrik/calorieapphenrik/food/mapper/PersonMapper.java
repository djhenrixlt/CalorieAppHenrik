package com.henrik.calorieapphenrik.food.mapper;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.Entity.Person;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import com.henrik.calorieapphenrik.food.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

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
}

