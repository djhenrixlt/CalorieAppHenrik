package com.henrik.calorieapphenrik.food.mapper;

import com.henrik.calorieapphenrik.food.Entity.Food;
import com.henrik.calorieapphenrik.food.Entity.MyList;
import com.henrik.calorieapphenrik.food.dto.FoodDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    FoodMapper FOOD_MAPPER = Mappers.getMapper(FoodMapper.class);

    FoodDto mapDto( Food food);

    Food mapModel(FoodDto foodDto);

    Food mapForUpdate(FoodDto foodDto, @MappingTarget Food food);

    MyList mapToListModel(FoodDto foodDto);
}
