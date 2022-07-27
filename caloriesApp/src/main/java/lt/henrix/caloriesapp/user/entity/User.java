package lt.henrix.caloriesapp.user.entity;

import lombok.*;

import lt.henrix.caloriesapp.UserGoals.entity.Goal;

import lt.henrix.caloriesapp.Userdetails.entity.UserInfo;
import lt.henrix.caloriesapp.food.Entity.Food;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
//    @NotBlank
    @Size(min = 3, max = 200)
    private String username;

    @Size(min=3, max=254)
    private String lastname;

    @Size(min=3, max=254)
    private String name;

    @Column(nullable = false)
//    @NotBlank
    @Size(min=3, max=254)
    private String password;

    @Size(min=3, max=254)
    private String email;

    private String phone;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="User_Roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles= new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserInfo userInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Goal Goal;

    @ManyToMany
    @JoinTable(
            name = "USER_FOODS",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods = new ArrayList<>();


    public void addRole(Role role){
        roles.add(role);
    }

    public void addFood(Food food){
        foods.add(food);
    }

    public void deleteFood(Food food){foods.remove(food);}


    public void addUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            this.userInfo = userInfo;
            userInfo.setUser(this);
        }
    }
    public void addGoal(Goal Goal) {
        if (Goal != null) {
            this.Goal = Goal;
            Goal.setUser(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
