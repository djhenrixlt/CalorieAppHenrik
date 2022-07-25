package lt.henrix.caloriesapp.food.service;

import lombok.AllArgsConstructor;
import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.food.Exception.FoodException;
import lt.henrix.caloriesapp.food.Repository.FoodRepo;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import lt.henrix.caloriesapp.food.mapper.FoodMapper;
import lt.henrix.caloriesapp.user.entity.User;
import lt.henrix.caloriesapp.user.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodRepo foodRepo;
    private  final UserRepo userRepo;


    public List<FoodDto> filter(String keyword) {
        if (keyword != null){
            return foodRepo.search(keyword)
                    .stream()
                    .map(FoodMapper.FOOD_MAPPER::mapDto)
                    .toList();
        }
        return foodRepo.findAll()
                .stream()
                .map(FoodMapper.FOOD_MAPPER::mapDto)
                .toList();
    }
    public List<FoodDto> getAllFoods() {
        return foodRepo.findAll()
                .stream()
                .map(FoodMapper.FOOD_MAPPER::mapDto)
                .toList();
    }

    public Optional<FoodDto> getFoodByName(String foodName) {
        Optional<Food> food = foodRepo.findByName(foodName);
        if (food.isEmpty()) {
            throw new FoodException("Food Not Exist");
        }
        return Optional.of(FoodMapper.FOOD_MAPPER.mapDto(food.get()));

    }

    public FoodDto updateFood(FoodDto foodDto, Long id) {
        boolean isExist = foodRepo.existsById(id);
        if (!isExist) {
            throw new FoodException("Food not exist");
        }
        Optional<Food> food = foodRepo.findById(id);
        Food update = FoodMapper.FOOD_MAPPER.mapForUpdate(foodDto, food.get());
        foodRepo.save(update);
        return FoodMapper.FOOD_MAPPER.mapDto(update);
    }


    public FoodDto saveFood(FoodDto food,String userName) {
        if (userRepo.findByUsername(userName).isEmpty()){
            throw new FoodException("you can not create food if you are not logged in");
        }
        Optional<User> user = userRepo.findByUsername(userName);
        food.setUserNameCreated(userName);
        Food saveFood = FoodMapper.FOOD_MAPPER.mapModel(food);
        foodRepo.save(saveFood);
        return FoodMapper.FOOD_MAPPER.mapDto(saveFood);
    }

    public void deleteFood(String name, String userName) {
        Optional<Food> food = foodRepo.findByName(name);

        if (food.get().getUserNameCreated().equals(userName)){
        Long id = food.get().getId();
        if (!foodRepo.existsById(id)) {
            throw new FoodException("food do not exist" + id);
        }
            foodRepo.delete(food.get());
        }
    }

    public Integer getCalories() {
        return foodRepo.getCaloriesSUm();
    }

}



