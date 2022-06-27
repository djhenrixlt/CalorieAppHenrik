package com.henrik.calorieapphenrik.Person.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Role  implements GrantedAuthority {

    @Id
    private Long id;

    private String roleName;

    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getAuthority() {
        return "ROLE_" + roleName;

    }
}
