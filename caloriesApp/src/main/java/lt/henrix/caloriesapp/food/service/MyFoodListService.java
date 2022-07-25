package lt.henrix.caloriesapp.food.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class MyFoodListService {

//    private final MyListRepo myListRepo;
//
//    private FoodService foodService;


//    public void deleteMyFood(Long foodId, Long personID) {
//        CaloriesDto caloriesDto = personService.findByID(personID);
//        Optional<MyList> food = myListRepo.findById(foodId);
//        if (!myListRepo.existsById(foodId)) {
//            throw new FoodException("id not exist" + foodId);
//        }
//        setUserGoals(caloriesDto, caloriesDto.getCaloriesConsumed() - food.get().getCalories());
//        Calories calories = CaloriesMapper.CALORIES_MAPPER.mapModel(caloriesDto);
//        Calories save = CaloriesMapper.CALORIES_MAPPER.mapForUpdate(caloriesDto, calories);
//        caloriesRepo.save(save);
//        caloriesDto.deleteFromMyList(food.get());
//
//        Calories calories2 = CaloriesMapper.CALORIES_MAPPER.mapModel(caloriesDto);
//        caloriesRepo.save(calories2);
//    }
//
//
//    public @NonNull Set<MyList> addToMyList(Long id, String name) {
//        Optional<FoodDto> foodDto = foodService.getFoodByName(name);
//        CaloriesDto caloriesDto = personService.findByID(id);
//        setUserGoals(caloriesDto, caloriesDto.getCaloriesConsumed() + foodDto.get().getCalories());
//        Calories calories = CaloriesMapper.CALORIES_MAPPER.mapModel(caloriesDto);
//        Calories save = CaloriesMapper.CALORIES_MAPPER.mapForUpdate(caloriesDto, calories);
//        caloriesRepo.save(save);
//        caloriesDto.addToMyLIst(foodDto.get());
//        Calories saveList = CaloriesMapper.CALORIES_MAPPER.mapModel(caloriesDto);
//        caloriesRepo.save(saveList);
//        return caloriesDto.getMyFoodList();
//    }
//
//    private void setUserGoals(CaloriesDto caloriesDto, int foodCalories) {
//        caloriesDto.setCaloriesConsumed(foodCalories);
//        caloriesDto.setCaloriesLeft(caloriesDto.getGoalCalories() - caloriesDto.getCaloriesConsumed());
//    }

}
