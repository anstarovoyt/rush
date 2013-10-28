package ru.naumen.mvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.auth.form.RegistrationForm;
import ru.naumen.core.auth.form.UserForm;
import ru.naumen.core.utils.UrlUtils;
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

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
    @Inject
    UserDAO userDAO;
    
    @RequestMapping(value = "/", method = GET)
    public String index(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("registrationForm", new RegistrationForm());
        return "index";
    }

    public static void noCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP-1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
    }

    @RequestMapping(value = "/complete-register/", method = POST)
    public String completeRegister(@ModelAttribute("registrationForm") RegistrationForm form, BindingResult result, HttpServletResponse response, Model model) {
        noCache(response);
        model.addAttribute("userForm", new UserForm());
        if (!Objects.equal(form.getPassword(), form.getConfirmPassword())) {
            result.rejectValue("password", "", "Пароли не совпадают");
            return "index";
        }
        String email = form.getEmail();
        if (userDAO.getByEmail(email) != null) {
            result.rejectValue("email", "", "Пользователь с таким email уже зарегистрирован");
            return "index";
        }
        User user = User.create(email, form.getFio(), form.getPassword());
        authenticator.registerUser(user);
        return "redirect:/games/?" + UrlUtils.createAKParam(user);
    }

    @RequestMapping(value = "/login/", method = POST)
    public String login(@ModelAttribute("userForm") UserForm userForm, HttpServletResponse response, BindingResult result, Model model) {
        noCache(response);
        model.addAttribute("registrationForm", new RegistrationForm());
        
        String email = userForm.getEmail();
        String password = userForm.getPassword();
        authenticator.checkAuth(email, password);
        if (!authenticator.checkAuth(email, password)) {
            result.rejectValue("email", "", "Неверный логин или пароль");
            return "index";
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
