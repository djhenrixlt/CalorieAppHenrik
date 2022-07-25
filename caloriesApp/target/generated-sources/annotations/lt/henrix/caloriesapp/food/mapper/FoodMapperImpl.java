package lt.henrix.caloriesapp.food.mapper;

import javax.annotation.processing.Generated;
import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.Entity.MyList;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-24T18:18:46+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class FoodMapperImpl implements FoodMapper {

    @Override
    public FoodDto mapDto(Food food) {
        if ( food == null ) {
            return null;
        }

        FoodDto foodDto = new FoodDto();

        foodDto.setName( food.getName() );
        foodDto.setCalories( food.getCalories() );
        foodDto.setProtein( food.getProtein() );
        foodDto.setCarbs( food.getCarbs() );
        foodDto.setFat( food.getFat() );
        foodDto.setFiber( food.getFiber() );
        foodDto.setUserNameCreated( food.getUserNameCreated() );

        return foodDto;
    }

    @Override
    public Food mapModel(FoodDto foodDto) {
        if ( foodDto == null ) {
            return null;
        }

        Food food = new Food();

        food.setName( foodDto.getName() );
        food.setCalories( foodDto.getCalories() );
        food.setProtein( foodDto.getProtein() );
        food.setCarbs( foodDto.getCarbs() );
        food.setFat( foodDto.getFat() );
        food.setFiber( foodDto.getFiber() );
        food.setUserNameCreated( foodDto.getUserNameCreated() );

        return food;
    }

    @Override
    public Food mapForUpdate(FoodDto foodDto, Food food) {
        if ( foodDto == null ) {
            return null;
        }

        food.setName( foodDto.getName() );
        food.setCalories( foodDto.getCalories() );
        food.setProtein( foodDto.getProtein() );
        food.setCarbs( foodDto.getCarbs() );
        food.setFat( foodDto.getFat() );
        food.setFiber( foodDto.getFiber() );
        food.setUserNameCreated( foodDto.getUserNameCreated() );

        return food;
    }

    @Override
    public MyList mapToListModel(FoodDto foodDto) {
        if ( foodDto == null ) {
            return null;
        }

        MyList myList = new MyList();

        myList.setName( foodDto.getName() );
        myList.setCalories( foodDto.getCalories() );
        myList.setProtein( foodDto.getProtein() );
        myList.setCarbs( foodDto.getCarbs() );
        myList.setFat( foodDto.getFat() );
        myList.setFiber( foodDto.getFiber() );

        return myList;
    }
}