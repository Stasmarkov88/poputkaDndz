package com.easygoapp.controllers;

import com.easygoapp.domain.User;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView logged() throws Exception {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() throws Exception {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user**", method = RequestMethod.GET)
    public ModelAndView dbaPage() throws Exception {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getByLogin(authentication.getName());
        model.addObject("adress", "user");
        model.addObject("user", currentUser);
        model.setViewName("user");

        return model;
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() throws Exception {
        ModelAndView model = new ModelAndView();
        model.setViewName("403");
        return model;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView showPromo() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());
        ModelAndView modelAndView = new ModelAndView("about");
        modelAndView.addObject("adress", "about");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
