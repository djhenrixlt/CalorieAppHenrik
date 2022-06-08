package com.henrik.calorieapphenrik.food.Entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Food {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private BigDecimal calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private Integer fiber;
}
