package com.easygoapp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Markov on 24.02.2015.
 */
@Entity
@Table(name = "PNP")
public class PassengerNodePoint implements Persistable<Long> {

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "isleft", nullable = false)
    private boolean left;

    @ManyToMany(mappedBy = "passengerNodePoints")
    private List<Trip> trips;

    @Id @GeneratedValue private Long id;

    public PassengerNodePoint() {
    }

    public PassengerNodePoint(boolean left, double latitude, double longitude) {
        this.left = left;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean isLeft) {
        this.left = isLeft;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PNP{" +
                "left=" + left +
                ", description='" + description + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", id=" + getId() +
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
        PassengerNodePoint rhs = (PassengerNodePoint) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(rhs))
                .append(this.latitude, rhs.latitude)
                .append(this.longitude, rhs.longitude)
                .append(this.description, rhs.description)
                .append(this.left, rhs.left)
                .append(this.trips, rhs.trips)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(super.hashCode())
                .append(latitude)
                .append(longitude)
                .append(description)
                .append(left)
                .append(trips)
                .toHashCode();
    }
}
