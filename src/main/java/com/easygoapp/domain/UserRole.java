package com.easygoapp.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Created by Stanislav Markov mailto: stasmarkov88@gmail.com
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole extends AbstractPersistable<Long> {

    @Column(name = "role", nullable = false)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
