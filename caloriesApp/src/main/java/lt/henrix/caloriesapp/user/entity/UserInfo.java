package lt.henrix.caloriesapp.user.entity;

import lombok.*;
import lt.henrix.caloriesapp.food.Entity.Food;
import lt.henrix.caloriesapp.user.entity.User;

import javax.persistence.*;
import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
public class UserInfo {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gender; // enum
    private Double age;
    private Double weight;
    private Double height;
    private String activityLevel; //enum
    private String plan; //enum



//    @OneToMany(mappedBy = "userInfo")
//    private Set<UserFood> userFoodsSet = new HashSet<>();


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

//    public UserInfo addFood(UserFood userFood){
//        userFood.setUserInfo(this);
//        this.userFoodsSet.add(userFood);
//        return this;
//    }
}
