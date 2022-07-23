package lt.henrix.caloriesapp.Userdetails.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import lt.henrix.caloriesapp.food.Entity.Food;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_FOODS")
public class UserFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
   private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private LocalDateTime addedAt;

    private int quantity;
}
