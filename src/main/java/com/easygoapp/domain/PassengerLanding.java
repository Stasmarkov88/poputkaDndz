package com.easygoapp.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PASSENGER_LANDING")
public class PassengerLanding implements Persistable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_passenger_id")
    private Trip trip;

    @OneToOne
    @JoinColumn(name = "id")
    private User passenger;

    @Column(name = "description", nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public User getUser() {
        return passenger;
    }

    public void setUser(User user) {
        this.passenger = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PassengerLanding{" +
                "id=" + id +
                ", trip=" + trip +
                ", passenger=" + passenger +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerLanding that = (PassengerLanding) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(trip, that.trip) &&
                Objects.equals(passenger, that.passenger) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trip, passenger, description);
    }
}
