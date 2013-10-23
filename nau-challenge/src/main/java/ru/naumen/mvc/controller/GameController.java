package ru.naumen.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: anstarovoyt
 * Date: 10/24/13
 * Time: 12:30 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GameController {g
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "game";

    }
}
