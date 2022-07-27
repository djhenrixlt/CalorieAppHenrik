package lt.henrix.caloriesapp.UserGoals.entity;

import lombok.*;
import lt.henrix.caloriesapp.user.entity.User;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Goals")
public class Goal {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer goalCalories;

    private Integer caloriesLeft;
    private Integer caloriesConsumed;
    private Integer goalProtein;
    private Integer goalCarbs;
    private Integer goalFats;


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;


}
