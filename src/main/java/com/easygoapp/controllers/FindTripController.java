package com.easygoapp.controllers;

import com.easygoapp.domain.PassengerLanding;
import com.easygoapp.domain.PassengerNodePoint;
import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import com.easygoapp.dto.FindTripObject;
import com.easygoapp.service.PassengerNodePointService;
import com.easygoapp.service.TripService;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Markov on 04.03.2015.
 */
@Controller
@RequestMapping("/user/findTrip")
public class FindTripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Autowired
    private PassengerNodePointService passengerNodePointService;

    private Long tripId;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String findTrip(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());
        List<PassengerNodePoint> points = passengerNodePointService.findAll();
        points.size();
        FindTripObject dto = new FindTripObject();
        dto.setPassengerNodePoints(points);
        List<Integer> listCount20 = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            listCount20.add(i);
        }
        model.addAttribute("adress", "findTrip");
        model.addAttribute("user", user);
        model.addAttribute("list", listCount20);
        model.addAttribute("points", points);
        model.addAttribute("dto", dto);
        return "findTrip";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String showResult(@ModelAttribute FindTripObject dto, Model model) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());

        //-----------------------GETTING DATES-------------------------------

        List<Trip> trips = tripService.getBetweenStartAndEnd(user.getId(), dto.getStartTime(), dto.getEndTime());

        //-----------------------SAVE NEEDED POINTS-------------------------------
        List<PassengerNodePoint> usersPoints;
        if ((usersPoints = dto.getPassengerNodePoints()) != null) {
            Iterator iterator = usersPoints.iterator();
            while (iterator.hasNext()) {
                PassengerNodePoint point = (PassengerNodePoint) iterator.next();
                if (point.getId() == null) {
                    iterator.remove();
                }
            }
        }
        dto.setTrips(trips);
        List<PassengerNodePoint> allPoints = passengerNodePointService.findAll();
        if (trips.isEmpty()){
            model.addAttribute("fail","fail");
        }
        model.addAttribute("dto", dto);
        model.addAttribute("points", allPoints);
        return "findTrip";
    }

    @RequestMapping(value = "/saveTrip", method = RequestMethod.GET)
    public String saveTrip(@RequestParam("id") long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("landing", new PassengerLanding());
        model.addAttribute("trip", tripService.findOneEager(id));
        tripId = id;
        return "confirmBooking";
    }

    @RequestMapping(value = "/confirmBooking", method = RequestMethod.POST)
    public String confirmBookingPlace(@ModelAttribute PassengerLanding passengerLanding) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());
        tripService.addPassenger(tripId, user.getId(), passengerLanding);
        return "redirect:/user/showTrips";
    }
}