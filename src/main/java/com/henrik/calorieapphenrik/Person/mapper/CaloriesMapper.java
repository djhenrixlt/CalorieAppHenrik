package com.henrik.calorieapphenrik.Person.mapper;

import com.henrik.calorieapphenrik.Person.dto.CaloriesDto;
import com.henrik.calorieapphenrik.Person.entity.Calories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CaloriesMapper {

    CaloriesMapper CALORIES_MAPPER = Mappers.getMapper(CaloriesMapper.class);


    CaloriesDto mapDto(Calories calories);

    Calories mapModel(CaloriesDto caloriesDto);

    @Mapping(target = "myFoodList", ignore = true)
    CaloriesDto mapDtoForSavePerson(Calories calories);
    @Mapping(target = "myFoodList", ignore = true)
    Calories mapModelSavePerson(CaloriesDto caloriesDto);


//    @Mapping(target = "myFoodList", ignore = true)
    Calories mapForUpdate(CaloriesDto caloriesDto, @MappingTarget Calories calories);

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
//    Calories mapModeFodMyList(CaloriesDto personDto);
}

