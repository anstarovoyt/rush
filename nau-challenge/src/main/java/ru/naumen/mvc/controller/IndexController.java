package ru.naumen.mvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.utils.UrlUtils;
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

    public static void noCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP-1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
    }

    @RequestMapping(value = "/complete-register/", method = POST)
    public String completeRegister(@RequestParam String email, @RequestParam String fio, @RequestParam String password,
            @RequestParam String confirmPassword, Model model, HttpServletResponse response) {
        noCache(response);
        if (!Objects.equal(password, confirmPassword)) {
            throw new RuntimeException();
        }
        User user = User.create(email, fio, password);
        authenticator.registerUser(user);
        return "redirect:/games/?" + UrlUtils.createAKParam(user);
    }

    @RequestMapping(value = "/login/", method = POST)
    public String login(@RequestParam String email, @RequestParam String password, Model model,
            HttpServletResponse response) {
        noCache(response);
        authenticator.checkAuth(email, password);
        if (!authenticator.checkAuth(email, password)) {
            throw new RuntimeException();
        }
        User user = authenticator.setCurrentUser(email);
        return "redirect:/games/?" + UrlUtils.createAKParam(user);
    }

    @RequestMapping(value = "/logout/", method = POST)
    public String logout(Model model, HttpServletResponse response) {
        noCache(response);
        authenticator.logout();
        return "redirect:/";
    }
}
