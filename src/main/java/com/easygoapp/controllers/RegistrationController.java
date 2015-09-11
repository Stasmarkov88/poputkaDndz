package com.easygoapp.controllers;

import com.easygoapp.domain.User;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user, ModelAndView modelAndView) throws Exception {
        if (user.getCar().equals("on,")) {
            user.setCar(null);
        } else {
            String str = user.getCar().substring(4);
            user.setCar(str);
        }
        try {
            userService.save(user);
            if (user.getCar() == null) {
                user.setCar("");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            modelAndView.setViewName("user");
            return modelAndView;
        } catch(DataIntegrityViolationException e) {
            user.setPassword(null);
            user.setLogin(null);
            modelAndView.addObject("user", user);
            modelAndView.addObject("message", "Пользователь с таким логином уже есть в базе данных!");
        }
        modelAndView.setViewName("registration");
        return modelAndView;
    }
}
