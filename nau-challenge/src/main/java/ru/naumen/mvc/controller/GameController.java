package ru.naumen.mvc.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.game.Game;
import ru.naumen.core.info.Params;

/**
 * Created with IntelliJ IDEA.
 * User: anstarovoyt
 * Date: 10/24/13
 * Time: 12:30 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GameController {

    @Inject
    Authenticator authenticator;

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String gameInfo(@RequestParam(value = Params.GAME_ID, required = false) String gid,
                           Model model) {

        Game game = authenticator.getCurrentUser().getUserGameStorage().get(gid);
        if (game != null) {
            model.addAttribute("description", game.getDescription());
            model.addAttribute("gid", gid);
        }
        return "game";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String gameProcess(@RequestParam(value = Params.GAME_ID, required = false) String gid,
                              @RequestParam(value = Params.ANSWER_ID, required = false) String answer,
                              Model model) {

        Game game = authenticator.getCurrentUser().getUserGameStorage().get(gid);

        if (game != null) {
            game.input(answer);

            model.addAttribute("description", game.getDescription());
            model.addAttribute("result", game.output());
        }

        return "game";
    }
}
