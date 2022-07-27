package lt.henrix.caloriesapp.food.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import lt.henrix.caloriesapp.user.entity.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Food {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private Integer fiber;

    private String userNameCreated;

//    @ManyToMany
//    @JoinColumn(name = "user_info_id", insertable = false, updatable = false)
//    private UserInfo userInfo;


    @ManyToMany(mappedBy = "foods")
    Set<User> userFoods = new HashSet<>();

//    @OneToMany(mappedBy = "food")
//    public Set<UserFood> userFoodsSet = new HashSet<>();
}
