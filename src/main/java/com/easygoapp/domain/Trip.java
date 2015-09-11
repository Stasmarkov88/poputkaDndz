package com.easygoapp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Markov on 24.02.2015.
 */
@Entity
@Table(name = "TRIP")
public class Trip implements Persistable<Long> {

    @Id @GeneratedValue private Long id;

    @Column(name = "start_trip", nullable = false)
    private Timestamp startTime;

    @ManyToOne
    private User driver;

    @OneToMany(mappedBy = "trip")
    private List<PassengerLanding> passengerLanding;

    @NotNull
    @Column(name = "car_capacity", nullable = false)
    private Integer carCapacity;

    @NotNull
    @Column(name = "price")
    private Double price;

    @ManyToMany
    @JoinTable(name = "TRIP_USER",
            joinColumns = {@JoinColumn(name = "trip_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> companions;

    @ManyToMany
    @JoinTable(name = "TRIP_POINTS",
            joinColumns = {@JoinColumn(name = "trip_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "pnp_id")})
    private List<PassengerNodePoint> passengerNodePoints;

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


    public List<PassengerLanding> getPassengerLanding() {
        return passengerLanding;
    }

    public void setPassengerLanding(List<PassengerLanding> passengerLanding) {
        this.passengerLanding = passengerLanding;
    }

    public List<PassengerNodePoint> getPassengerNodePoints() {
        return passengerNodePoints;
    }

    public void setPassengerNodePoints(List<PassengerNodePoint> passengerNodePoints) {
        this.passengerNodePoints = passengerNodePoints;
    }

    public List<User> getCompanions() {
        return companions;
    }

    public void setCompanions(List<User> companions) {
        this.companions = companions;
    }

    public Integer getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(Integer carCapacity) {
        this.carCapacity = carCapacity;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "carCapacity=" + carCapacity +
                ", id=" + getId() +
                ", startTime=" + startTime +
                ", driver=" + driver +
                ", price=" + price +
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
        Trip rhs = (Trip) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(rhs))
                .append(this.startTime, rhs.startTime)
                .append(this.driver, rhs.driver)
                .append(this.carCapacity, rhs.carCapacity)
                .append(this.price, rhs.price)
                .append(this.passengerNodePoints, rhs.passengerNodePoints)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(startTime)
                .append(driver)
                .append(carCapacity)
                .append(price)
                .append(passengerNodePoints)
                .toHashCode();
    }
}
