package com.henrik.calorieapphenrik.user.Enum;

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
