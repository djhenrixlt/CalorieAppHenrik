package lt.henrix.caloriesapp.UserGoals.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.henrix.caloriesapp.UserGoals.entity.Goal;
import lt.henrix.caloriesapp.Userdetails.dto.UserInfoDto;
import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import lt.henrix.caloriesapp.food.Exception.FoodException;
import lt.henrix.caloriesapp.food.dto.FoodDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Repository
public class Counter {

    private static final String WOMAN = "woman";
    private static final String MEN = "men";
    private static final String SEDENTARY = "sedentary";
    private static final String LIGHTLY = "lightly";
    private static final String MODERATELY = "moderately";
    private static final String ACTIVE = "active";
    private static final String VERY = "very";

public void setGoals(UserInfo userInfo, Goal goal) throws GoalException {
//        person.setGoalCalories(countBmr(caloriesDto).intValue());
//        person.setCaloriesConsumed(0);
//        person.setCaloriesLeft(countBmr(caloriesDto).intValue());

        goal.setGoalCalories(countYourPlan(userInfo.getPlan(), countBmr(userInfo).intValue()).intValue());
        goal.setCaloriesConsumed(0);
        goal.setCaloriesLeft(countYourPlan(userInfo.getPlan(), countBmr(userInfo).intValue()).intValue());
        goal.setGoalProtein(countProteinGoal(userInfo.getActivityLevel(), userInfo.getWeight()).intValue());
        goal.setGoalCarbs(getCarbsGoal(goal.getGoalCalories()));
        goal.setGoalFats(getFatGoal(goal.getGoalCalories()).intValue());
    }

    private Double getFatGoal(Integer goalCal) {
        return (goalCal * 0.3) / 9;
    }

    private int getCarbsGoal(Integer calories) {
        return (calories / 2) / 4;
    }

    private Double countBmr(UserInfo userInfo) throws GoalException {
        return switch (userInfo.getGender()) {
            case WOMAN -> countAmr(userInfo.getActivityLevel(), getBmrWoman(userInfo));
            case MEN -> countAmr(userInfo.getActivityLevel(), getBmrMen(userInfo));
            default -> throw new GoalException("gender not exist: " + userInfo.getGender());
        };
    }

    private double countAmr(String activity, Double bmr) {
        return switch (activity) {
            case SEDENTARY -> bmr * 1.2;
            case LIGHTLY -> bmr * 1.375;
            case MODERATELY -> bmr * 1.55;
            case ACTIVE -> bmr * 1.725;
            case VERY -> bmr * 1.9;
            default -> throw new FoodException("Your activity level not exist: " + activity);
        };
    }

    private Double countProteinGoal(String activity, Double weight) {
        return switch (activity) {
            case SEDENTARY -> weight * 0.7;
            case LIGHTLY -> weight * 0.9;
            case MODERATELY -> weight * 1.5;
            case ACTIVE -> weight * 1.8;
            case VERY -> weight * 2;
            default -> throw new FoodException("Your activity level not exist: " + activity);
        };
    }

    private Double countYourPlan(String level, Integer goalCalories) {
        return switch (level) {
            case "lost10" -> goalCalories * 0.9;
            case "lost20" -> goalCalories * 0.8;
            case "gain1" -> goalCalories + 200.0;
            case "gain2" -> goalCalories + 500.0;
            case "same" -> goalCalories.doubleValue();
            default -> throw new FoodException("Your activity level not exist: " + level);
        };
    }

    private double getBmrWoman(UserInfo userInfo) {
        return 655.1 + (9.563 * userInfo.getWeight()) + (1.850 * userInfo.getHeight()) - (4.676 * userInfo.getAge());
    }

    private double getBmrMen(UserInfo userDto) {
        return 66.47 + (13.75 * userDto.getWeight()) + (5.003 * userDto.getHeight()) - (6.755 * userDto.getAge());
    }

    private boolean isEquals(String firsValue, String secondValue) {
        return firsValue.equals(secondValue);
    }


    private Optional<FoodDto> getFoodList(String foodName, List<FoodDto> foodDtoList) {
        return foodDtoList.stream()
                .filter(food -> food.getName().equals(foodName))
                .findAny();
    }

}
