package ru.naumen.mvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер страницы входа
 * 
 * @author serce
 * @since 24 окт. 2013 г.
 */
@Controller
public class IndexController {
    
    @RequestMapping(value = "/complete-register/", method = POST)
    public String completeRegister(@RequestParam String email, @RequestParam String fio, @RequestParam String password,@RequestParam String confirmPassword) {
        return "game";
    }
} 
