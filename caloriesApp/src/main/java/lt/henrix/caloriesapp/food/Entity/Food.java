package lt.henrix.caloriesapp.food.Entity;

import lombok.*;

import lt.henrix.caloriesapp.user.entity.User;

import javax.persistence.*;
import java.util.Set;

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
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private Integer fiber;

    private String userNameCreated;

//    @ManyToMany
//    @JoinColumn(name = "user_info_id", insertable = false, updatable = false)
//    private UserInfo userInfo;


//    @ManyToMany
//    Set<User> registrations;

//    @OneToMany(mappedBy = "food")
//    public Set<UserFood> userFoodsSet = new HashSet<>();
}
