package com.easygoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/maps")
public class MapsController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showMapsPage() {
        return "maps";
    }
}
