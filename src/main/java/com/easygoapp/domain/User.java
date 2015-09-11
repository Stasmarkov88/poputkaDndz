package com.easygoapp.domain;

import com.easygoapp.type.Gender;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Markov on 24.02.2015.
 */

@Entity
@Table(name = "USER")
public class User extends AbstractPersistable<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "car")
    private String car;

    @Email
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "driver")
    private List<Trip> tripsWhereUserDriver;

    @ManyToMany(mappedBy = "companions")
    private List<Trip> trips;

    @OneToOne(mappedBy = "passenger")
    private PassengerLanding passengerLanding;

    //Security
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "login", referencedColumnName = "login")
    private List<UserRole> userRoles;

    public User() {
    }

    public User(Gender gender, String name, String password) {
        this.gender = gender;
        this.name = name;
        this.password = password;
    }

    public List<Trip> getTripsWhereUserDriver() {
        return tripsWhereUserDriver;
    }

    public void setTripsWhereUserDriver(List<Trip> tripsWhereUserDriver) {
        this.tripsWhereUserDriver = tripsWhereUserDriver;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public PassengerLanding getPassengerLanding() {
        return passengerLanding;
    }

    public void setPassengerLanding(PassengerLanding passengerLanding) {
        this.passengerLanding = passengerLanding;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void addUserRole(UserRole role) {
        if (!getUserRoles().contains(role)) {
            getUserRoles().add(role);
        }
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", car='" + car + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        User rhs = (User) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(rhs))
                .append(this.name, rhs.name)
                .append(this.login, rhs.login)
                .append(this.gender, rhs.gender)
                .append(this.password, rhs.password)
                .append(this.phoneNumber, rhs.phoneNumber)
                .append(this.car, rhs.car)
                .append(this.trips, rhs.trips)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(name)
                .append(login)
                .append(gender)
                .append(password)
                .append(phoneNumber)
                .append(car)
                .append(trips)
                .toHashCode();
    }
}
