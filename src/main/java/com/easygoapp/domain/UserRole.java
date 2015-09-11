package com.easygoapp.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Kir Kolesnikov on 04.03.2015.
 */
@Entity
@Table(name = "user_role")
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
