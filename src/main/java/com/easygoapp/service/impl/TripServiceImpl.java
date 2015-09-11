package com.easygoapp.service.impl;

import com.easygoapp.domain.PassengerLanding;
import com.easygoapp.domain.PassengerNodePoint;
import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import com.easygoapp.repository.PassengerLandingRepository;
import com.easygoapp.repository.PassengerNodePointRepository;
import com.easygoapp.repository.TripRepository;
import com.easygoapp.repository.UserRepository;
import com.easygoapp.service.PassengerLandingService;
import com.easygoapp.service.PassengerNodePointService;
import com.easygoapp.service.TripService;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by Станислав on 28.02.2015.
 */
@Transactional(readOnly = true)
@Service
public class TripServiceImpl extends AbstractCrudServiceImpl<Trip, Long> implements TripService {

    private TripRepository tripRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PassengerNodePointService passengerNodePointService;

    @Autowired
    private PassengerLandingService passengerLandingService;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public List<Trip> getBetweenStartAndEnd(Long id, String start, String end) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = dateFormat.parse(start);
        long time = date.getTime();
        Timestamp startTrip = new Timestamp(time);
        date = dateFormat.parse(end);
        time = date.getTime();
        Timestamp endTrip = new Timestamp(time);
        List<Trip> trips = tripRepository.findByCarCapacityGreaterThanAndStartTimeBetween(0, startTrip,
                endTrip);

        Iterator iterator = trips.iterator();
        while (iterator.hasNext()) {
            boolean flag = false;
            Trip trip = (Trip) iterator.next();
            if (id.equals(trip.getDriver().getId())) {
                iterator.remove();
            }
            trip.getPassengerNodePoints().size();
            trip.getCompanions().size();
            for (User user : trip.getCompanions()) {
                if (user.getId().equals(id)) {
                    flag = true;
                }
            }
            if (flag) {
                iterator.remove();
            }
        }
        return trips;
    }

    @Autowired
    public void setTripRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
        super.setRepository(tripRepository);
    }

    @Override
    public Trip findOneEager(Long id) {
        Trip trip = super.findOne(id);
        trip.getCompanions().size();
        trip.getPassengerNodePoints().size();
        trip.getPassengerLanding().size();
        return trip;
    }

    @Override
    public List<Trip> findAllFutureTripsForPassenger(User user) {
        long time = new Date().getTime();
        Timestamp stamp = new Timestamp(time);
        List<Trip> trips = tripRepository.findBycompanionsAndStartTimeGreaterThan(user, stamp);
        Iterator iterator = trips.iterator();
        while (iterator.hasNext()) {
            Trip trip = (Trip) iterator.next();
            if (Objects.equals(user.getId(), trip.getDriver().getId())) {
                iterator.remove();
            }
        }
        for (Trip trip : trips) {
            trip.getCompanions().size();
            trip.getPassengerNodePoints().size();
            trip.getPassengerLanding().size();
        }
        return trips;
    }

    @Override
    public List<Trip> findAllFutureTripsForDriver(User user) {
        long time = new Date().getTime();
        Timestamp stamp = new Timestamp(time);
        List<Trip> trips = tripRepository.findByDriverAndStartTimeGreaterThan(user, stamp);
        for (Trip trip : trips) {
            trip.getCompanions().size();
            trip.getPassengerNodePoints().size();
            trip.getPassengerLanding().size();
        }
        return trips;
    }

    @Override
    public List<Trip> findAllLastTripsForDriver(User user) {
        long time = new Date().getTime();
        Timestamp stamp = new Timestamp(time);
        List<Trip> trips = tripRepository.findByDriverAndStartTimeLessThan(user, stamp);
        for (Trip trip : trips) {
            trip.getCompanions().size();
            trip.getPassengerNodePoints().size();
            trip.getPassengerLanding().size();
        }
        return trips;
    }

    @Transactional
    @Override
    public void removeCompanionFromTrip(Long companionId, Long tripId) throws MessagingException {
        Trip trip = findOneEager(tripId);
        User passenger = userService.findOne(companionId);
        List<User> users = trip.getCompanions();
        Iterator iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            if (Objects.equals(user.getId(), companionId)) {
                iterator.remove();
            }
        }
        trip.setCarCapacity(trip.getCarCapacity() + 1);
        Iterator iterator1 = trip.getPassengerLanding().iterator();
        while (iterator1.hasNext()){
            PassengerLanding landing = (PassengerLanding) iterator1.next();
            if (Objects.equals(landing.getUser().getId(), companionId)){
                passengerLandingService.delete(landing.getId());
                iterator1.remove();
            }
        }
        tripRepository.save(trip);
    }

    @Override
    @Transactional
    public void addPassenger(Long tripId, Long userId, PassengerLanding landing) {
        Trip currentTrip = findOneEager(tripId);
        User passenger = userService.findOne(userId);
        currentTrip.getCompanions().add(passenger);
        currentTrip.setCarCapacity(currentTrip.getCarCapacity() - 1);
        landing.setTrip(currentTrip);
        landing.setUser(passenger);
        passengerLandingService.save(landing);
        tripRepository.save(currentTrip);
    }

    @Override
    @Transactional
    public void addPassengerNodePoint(Long tripId, Long passengerNodePointId) {
        Trip currentTrip = tripRepository.findOne(tripId);
        PassengerNodePoint passengerNodePoint = passengerNodePointService.findOne(passengerNodePointId);
        List<PassengerNodePoint> passengerNodePoints = currentTrip.getPassengerNodePoints();
        passengerNodePoints.add(passengerNodePoint);
        tripRepository.save(currentTrip);
    }

    @Override
    @Transactional
    public void removePassengerNodePoint(Long tripId, Long passengerNodePointId) {
        Trip currentTrip = tripRepository.findOne(tripId);
        PassengerNodePoint passengerNodePoint = passengerNodePointService.findOne(passengerNodePointId);
        List<PassengerNodePoint> passengerNodePoints = currentTrip.getPassengerNodePoints();
        passengerNodePoints.remove(passengerNodePoint);
        tripRepository.save(currentTrip);
        for (User passenger : currentTrip.getCompanions()) {
            //TODO passengers notification
        }
    }

    @Override
    @Transactional
    public void setPassengerNodePointsList(Long tripId, List<PassengerNodePoint> points) {
        Trip currentTrip = tripRepository.findOne(tripId);
        currentTrip.setPassengerNodePoints(points);
        tripRepository.save(currentTrip);
    }

    @Transactional
    @Override
    public void delete(Long id) throws MessagingException {
        tripRepository.delete(id);
    }

    @Override
    @Transactional
    public Trip modifyTrip(Trip trip) {
        Trip originalTrip = tripRepository.findOne(trip.getId());
        Trip modifiedTrip;
        List<User> companions = originalTrip.getCompanions();
        if (trip.getCarCapacity() > originalTrip.getCarCapacity()) {
            modifiedTrip = tripRepository.save(trip);
            //TODO passengers notification
            return modifiedTrip;
        } else {
            return originalTrip;
        }
    }

    @Override
    @Transactional
    public void cancelTrip(Long id) throws MessagingException {
        Trip trip = findOneEager(id);
        List<User> companions = trip.getCompanions();
        if (companions.size() > 0) {
            for (User companion : companions) {
                sendMessage(companion, trip);
            }
        }
        tripRepository.delete(id);
    }

    private void sendMessage(User companion, Trip trip) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        mailMsg.setFrom("easygodndz@gmail.com");
        mailMsg.setTo(companion.getEmail());
        mailMsg.setSubject("Водитель отменил поездку");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Добрый день уважаемый ");
        stringBuilder.append(companion.getName());
        stringBuilder.append(". Мы очень рады, что Вы являетесь пользователем нашего");
        stringBuilder.append(" проекта Easygo Днепродзержинск.\n");
        stringBuilder.append("Извещаем Вас о том, что водитель отменил поездку: ");
        stringBuilder.append(trip.getStartTime());
        stringBuilder.append("\nПриносим извинения за доставленные неудобства. Чтобы перейти к поиску новой поездки,");
        stringBuilder.append("пройдите по следующей ссылке: http://localhost:8080/");
        stringBuilder.append("\n\n\nС уважением, администрация Easygo Днепродзержинск");
        stringBuilder.append(
                "\nБудем рады Вашим пожеланиям и отзывам о нашем проекте. Напишите нам easygodndz@gmail.com");
        mailMsg.setText(stringBuilder.toString());
        mailSender.send(mimeMessage);
    }
}
