package com.henrik.calorieapphenrik.Person.Enum;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum Plan {
   LOST_10("lost10"),
    LOST_20("lost20"),
    GAIN_1("gain1"),
    GAIN_2("gain2"),
    SAME("same");

   private String plan;

    Plan(String plan) {
        this.plan = plan;
    }
}
