package com.henrik.calorieapphenrik.Person.Enum;

public enum ActivityLevel {
    SEDENTARY ("sedentary"),
    LIGHTLY ("lightly"),
    MODERATELY ("moderately"),
    ACTIVE ("active"),
    VERY ("very");

    private String activityLevel;

    ActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}
