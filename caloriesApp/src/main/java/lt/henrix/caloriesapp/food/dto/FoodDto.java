package lt.henrix.caloriesapp.food.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 1, max = 2000)
    private Integer calories;
    @NotBlank
    private Integer protein;
    @NotBlank
    private Integer carbs;
    @NotBlank
    private Integer fat;
    @NotBlank
    private Integer fiber;

    private String userNameCreated ;
}

