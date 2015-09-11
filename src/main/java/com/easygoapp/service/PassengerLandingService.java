package com.easygoapp.service;

import com.easygoapp.domain.PassengerLanding;
import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public interface PassengerLandingService extends AbstractCrudService<PassengerLanding, Long> {
    PassengerLanding findByTripAndPassenger(Trip trip, User user);
}
