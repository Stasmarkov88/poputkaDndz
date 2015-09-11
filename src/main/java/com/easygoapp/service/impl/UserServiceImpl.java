package com.easygoapp.service.impl;

import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import com.easygoapp.domain.UserRole;
import com.easygoapp.repository.UserRepository;
import com.easygoapp.service.TripService;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Станислав on 28.02.2015.
 */

@Service
public class UserServiceImpl extends AbstractCrudServiceImpl<User, Long> implements UserService {

    private UserRepository userRepository;

    @Autowired
    private TripService tripService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        super.setRepository(userRepository);
    }

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            String cryptedPassword = encoder.encode(user.getPassword());
            user.setPassword(cryptedPassword);
        }
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        List<UserRole> userRoles = new ArrayList<UserRole>();
        userRoles.add(userRole);
        user.setUserRoles(userRoles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void delete(Long id) throws MessagingException {
        User user = userRepository.findOne(id);
        //=======================CANCEL AS PASSENGER==================================
        List<Trip> tripsAsPassenger = tripService.findAllFutureTripsForPassenger(user);
        for (Trip trip: tripsAsPassenger){
            tripService.removeCompanionFromTrip(id, trip.getId());
        }
        //=======================CANCEL AS DRIVER=====================================
        List<Trip> trpsAsDriver = tripService.findAllFutureTripsForDriver(user);
        if(trpsAsDriver.size()>0) {
            for (Trip trip : trpsAsDriver) {
                tripService.cancelTrip(trip.getId());
            }
        }
        //=======================CANCEL LAST TRIPS DRIVER==============================
        List<Trip> lastTripsAsDriver = tripService.findAllLastTripsForDriver(user);
        if(lastTripsAsDriver.size()>0) {
            for (Trip trip : trpsAsDriver) {
                tripService.delete(trip.getId());
            }
        }
        //=======================FINALLY DELETE USER===================================
        userRepository.delete(id);
    }
}
