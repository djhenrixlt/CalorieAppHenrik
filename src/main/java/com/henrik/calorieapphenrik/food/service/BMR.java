package com.henrik.calorieapphenrik.food.service;


import com.henrik.calorieapphenrik.food.Exception.FoodException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class BMR {

    public static final String WOMAN = "woman";
    public static final String MEN = "men";
    public static final String SEDENTARY = "Sedentary";
    public static final String LIGHTLY = "lightly";
    public static final String MODERATELY = "moderately";
    public static final String ACTIVE = "active";
    public static final String VERY = "very";
    private Double bmr;
    private Double amr;

    public Double countBmr(String gender, Integer weight, Integer height, Integer age){
        if (Objects.equals(gender, WOMAN)){
            return bmr = 655.1+(9.563*weight)+(1.850*height)-(4.676*age);
        } else if (Objects.equals(gender, MEN)) {
            return bmr = 66.47+(13.75*weight)+(5.003*height)-(6.755*age);

        }
            throw new FoodException("gender not exist: "+gender);
    }

    public Double countAmr(String activity) {
        if (isEquals(activity,SEDENTARY)) {
            return amr = bmr * 1.2;
        } else if (isEquals(activity, LIGHTLY)) {
            return amr = bmr * 1.375;
        } else if (isEquals(activity, MODERATELY)) {
            return amr = bmr * 1.55;
        } else if (isEquals(activity, ACTIVE)) {
            return amr = bmr * 1.725;
        } else if (isEquals(activity, VERY)) {
            return amr = bmr * 1.9;
        }
        throw new FoodException("Your activity level not exist: " + activity);
    }

    private boolean isEquals(String activity, String level) {
        return activity.toLowerCase().equals(level);
    }
}
