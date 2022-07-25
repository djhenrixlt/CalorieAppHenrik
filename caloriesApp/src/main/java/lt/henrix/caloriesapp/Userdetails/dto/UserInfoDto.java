package lt.henrix.caloriesapp.Userdetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

    private long id;

    private String gender; // enum
    private Double age;
    private Double weight;
    private Double height;
    private String activityLevel; //enum
    private String plan; //enum


}
