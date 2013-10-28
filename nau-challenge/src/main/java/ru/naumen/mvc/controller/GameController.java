package ru.naumen.mvc.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameSeries;
import ru.naumen.core.game.GameSeriesState;
import ru.naumen.core.game.GameState;
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

        GameSeries gameSeries = authenticator.getCurrentUser().getUserGameStorage().get( gid );
        if (gameSeries.getState() == GameSeriesState.CLOSED)
            return "gameclosed";
        if (gameSeries.getState() == GameSeriesState.SOLVED) {
            model.addAttribute("wins", gameSeries.wonGamesCount());
            return "gamesolved";
        }
        Game game = gameSeries.getGame();

        model.addAttribute("description", game.getDescription());
        model.addAttribute("gid", gid);
        model.addAttribute("maxwins", gameSeries.maxWinsCount());
        model.addAttribute("wins", gameSeries.wonGamesCount());

        return "rungame";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String gameProcess(@RequestParam(value = Params.GAME_ID, required = false) String gid,
                              @RequestParam(value = Params.ANSWER_ID, required = false) String answer,
                              Model model) {

        GameSeries gameSeries = authenticator.getCurrentUser().getUserGameStorage().get( gid );
        Game game = gameSeries.getGame();

        game.input(answer);
        // вопрос, когда высчитывается состояние игры
        if (game.state() == GameState.VICTORY)
            gameSeries.winOneGame();
        if (game.state() == GameState.FAILURE)
            gameSeries.loseOneGame();

        if (gameSeries.getState() == GameSeriesState.SOLVED) {
            model.addAttribute( "wins", gameSeries.wonGamesCount() );
            return "gamesolved";
        }

        model.addAttribute("description", game.getDescription());
        model.addAttribute("result", game.output());
        model.addAttribute("maxwins", gameSeries.maxWinsCount());
        model.addAttribute("wins", gameSeries.wonGamesCount());

        return "rungame";
    }
}
