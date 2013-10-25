package ru.naumen.mvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.model.User;

import com.google.common.base.Objects;

/**
 * Контроллер страницы входа
 * 
 * @author serce
 * @since 24 окт. 2013 г.
 */
@Controller
public class IndexController {
    
    @Inject
    Authenticator authenticator;
    
    @RequestMapping(value = "/complete-register/", method = POST)
    public String completeRegister(@RequestParam String email, @RequestParam String fio, @RequestParam String password,@RequestParam String confirmPassword, Model model) {
        if(!Objects.equal(password, confirmPassword)) {
            throw new RuntimeException();
        }
        User user = User.create(email, fio, password);
        authenticator.registerUser(user);
        return "redirect:/games/";
    }
} 
