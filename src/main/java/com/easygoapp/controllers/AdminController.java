package com.easygoapp.controllers;

import com.easygoapp.domain.PassengerNodePoint;
import com.easygoapp.domain.User;
import com.easygoapp.service.PassengerNodePointService;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/**")
public class AdminController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserService userService;

    @Autowired
    PassengerNodePointService passengerNodePointService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public ModelAndView getUsersList(ModelAndView modelAndView) {
        List<User> allUsers = userService.findAll();
        modelAndView.addObject("usersList", allUsers);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

//    @RequestMapping(value = "/edit", method = {RequestMethod.GET, RequestMethod.HEAD})
//    public ModelAndView editProfile(ModelAndView modelAndView, HttpServletRequest request) {
//        Long id = Long.parseLong(request.getParameter("id"));
//        User userToEdit = userService.findOne(id);
//        modelAndView.addObject("user", userToEdit);
//        modelAndView.setViewName("editProfile");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public ModelAndView saveUserChanges(ModelAndView modelAndView, @ModelAttribute("user") User user,
//            BindingResult result) {
//        User modifiedUser = userService.getByLogin(user.getLogin());
//        modifiedUser.setName(user.getName());
//        modifiedUser.setEmail(user.getEmail());
//        modifiedUser.setPhoneNumber(user.getPhoneNumber());
//        modifiedUser.setGender(user.getGender());
//        modifiedUser.setCar(user.getCar());
//        userService.save(modifiedUser);
//        modelAndView.setViewName("redirect:/admin");
//        return modelAndView;
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(ModelAndView modelAndView, HttpServletRequest request) throws MessagingException {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.delete(id);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @RequestMapping(value = "/pointsList", method = {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView getPointsList(ModelAndView modelAndView) {
        List<PassengerNodePoint> points = passengerNodePointService.findAll();
        points.size();
        modelAndView.addObject("points", points);
        modelAndView.setViewName("adminPointsManager");
        return modelAndView;
    }

    @RequestMapping(value = "/addPoint", method = RequestMethod.GET)
    public ModelAndView customPoint(ModelAndView modelAndView) {
        PassengerNodePoint point = new PassengerNodePoint();
        modelAndView.addObject("point", point);
        modelAndView.setViewName("addNewPNP");

        return modelAndView;
    }

    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
    public ModelAndView addNewPoint(ModelAndView modelAndView, @ModelAttribute("point") PassengerNodePoint point,
            BindingResult result, HttpServletRequest request) {
        passengerNodePointService.save(point);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }
}
