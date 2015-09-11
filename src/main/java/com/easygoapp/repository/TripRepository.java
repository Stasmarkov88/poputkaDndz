package com.easygoapp.repository;

import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Станислав on 28.02.2015.
 */

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByCarCapacityGreaterThanAndStartTimeBetween(Integer noll, Timestamp start, Timestamp end);

    List<Trip> findByDriverAndStartTimeGreaterThan(User user, Timestamp time);

    List<Trip> findBycompanionsAndStartTimeGreaterThan(User user, Timestamp stamp);

    List<Trip> findByDriverAndStartTimeLessThan(User user, Timestamp time);
}
