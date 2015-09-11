package com.easygoapp.controllers;

import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import com.easygoapp.service.PassengerNodePointService;
import com.easygoapp.service.TripService;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by Markov on 08.03.2015.
 */
@Controller
@RequestMapping("/user/showTrips")
public class ShowUserTripsController {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Autowired
    private PassengerNodePointService passengerNodePointService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showTrips(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());
        List<Trip> tripsDriver = tripService.findAllFutureTripsForDriver(user);
        List<Trip> tripsPassenger = tripService.findAllFutureTripsForPassenger(user);
        model.addAttribute("adress","showTrips");
        model.addAttribute("user", user);
        model.addAttribute("tripsDriver", tripsDriver);
        model.addAttribute("tripsPassenger", tripsPassenger);
        return "showTrips";
    }

    @RequestMapping(value = "/deleteTrip", method = RequestMethod.GET)
    public String deleteTrip(@RequestParam("id") long id) throws MessagingException {
        tripService.cancelTrip(id);
        return "redirect:/user/showTrips";
    }

    @RequestMapping(value = "/declineTrip", method = RequestMethod.GET)
    public String declineTrip(@RequestParam("id") long id) throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());
        tripService.removeCompanionFromTrip(user.getId(), id);
        return "redirect:/user/showTrips";
    }
}