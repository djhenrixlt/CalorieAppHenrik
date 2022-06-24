package com.henrik.calorieapphenrik.Person.entity;

import com.henrik.calorieapphenrik.food.Entity.MyList;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.
        SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serial;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@RequiredArgsConstructor
public class Person  implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private final String username;
    private final String password;
    private final String fullName;
    private final String email;
    private final String gender;
    private final Double age;
    private final Double weight;
    private final Double height;
    private final String activityLevel;
    private final Integer goalCalories;
    private final Integer caloriesLeft;
    private final Integer caloriesConsumed;
    private final Integer goalProtein;
    private final Integer goalCarbs;
    private final Integer goalFats;
    private final String plan;

    @OneToMany(cascade = CascadeType.ALL)
    @NonNull
    private Set<MyList> myFoodList =  new HashSet<>();




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("Role_USER"));
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

