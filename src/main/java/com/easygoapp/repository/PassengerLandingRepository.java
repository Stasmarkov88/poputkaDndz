package com.easygoapp.repository;

import com.easygoapp.domain.PassengerLanding;
import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerLandingRepository extends JpaRepository<PassengerLanding, Long> {

    PassengerLanding findByTripAndPassenger(Trip trip, User user);
}
