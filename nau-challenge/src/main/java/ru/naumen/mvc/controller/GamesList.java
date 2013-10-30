package ru.naumen.mvc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameSeries;
import ru.naumen.model.dao.UserDAO;

/**
 * @author Andrey Hitrin
 * @since 30.10.13
 */
@Controller
public class GamesList
{
    @Inject
    Authenticator authenticator;

    @Inject
    UserDAO dao;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public String gamesList(Model model) {
        Collection<GameSeries> games = authenticator.getCurrentUser().getUserGameStorage().getAll();
        List<Game> gameIds = new ArrayList<>();
        for (GameSeries series : games) {
            gameIds.add( series.getGame() );
        }
        model.addAttribute( "gameIds", gameIds );
        return "allgames";
    }
}
