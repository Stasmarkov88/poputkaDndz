package com.easygoapp.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//Comment this annotation to view the stacktraces while developing
@ControllerAdvice
public class AppExceptionHandlerController {


    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);
        model.addObject("message", "ОЙ! Что то пошло не так!");
        return model;
    }

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDataException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);
        model.addObject("message", "Произошла ошибка при работе с Базой Данных");
        return model;
    }
}
