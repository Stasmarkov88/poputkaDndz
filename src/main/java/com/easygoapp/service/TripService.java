package com.easygoapp.service;

import com.easygoapp.domain.PassengerLanding;
import com.easygoapp.domain.PassengerNodePoint;
import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Станислав on 28.02.2015.
 */
@Service
public interface TripService extends AbstractCrudService<Trip, Long> {

    Trip findOneEager(Long id);

    List<Trip> getBetweenStartAndEnd(Long id, String start, String end) throws ParseException;

    List<Trip> findAllFutureTripsForPassenger(User user);

    List<Trip> findAllFutureTripsForDriver(User user);

    List<Trip> findAllLastTripsForDriver(User user);

    void removeCompanionFromTrip(Long companionId, Long tripId) throws MessagingException;

    void addPassenger(Long tripId, Long userId, PassengerLanding landing);

    void addPassengerNodePoint(Long tripId, Long passengerNodePointId);

    void removePassengerNodePoint(Long tripId, Long passengerNodePointId);

    void setPassengerNodePointsList(Long tripId, List<PassengerNodePoint> points);

    Trip modifyTrip(Trip trip);

    void cancelTrip(Long id) throws MessagingException;
}
