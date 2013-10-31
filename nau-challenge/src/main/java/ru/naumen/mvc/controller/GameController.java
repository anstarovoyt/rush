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
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

/**
 * User: anstarovoyt
 * Date: 10/24/13
 * Time: 12:30 AM
 */
@Controller
public class GameController
{

    @Inject
    Authenticator authenticator;

    @Inject
    UserDAO dao;


    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String gameInfo(@RequestParam(value = Params.GAME_ID, required = false) String gid, Model model)
    {

        GameSeries gameSeries = authenticator.getCurrentUser().getUserGameStorage().get(gid);
        if (isClosed(gameSeries))
        {
            return "gameclosed";
        }
        if (isSolved(gameSeries))
        {
            model.addAttribute("wins", gameSeries.wonGamesCount());
            return "gamesolved";
        }
        Game game = gameSeries.getGame();

        model.addAttribute("description", game.getDescription());
        model.addAttribute("gid", gid);
        model.addAttribute("maxwins", gameSeries.maxWinsCount());
        model.addAttribute("wins", gameSeries.wonGamesCount());
        model.addAttribute("computerstate", game.computerOutput());
        model.addAttribute("computermessage", game.output());
        model.addAttribute("gamestate", game.state().getMessage());

        return "rungame";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String gameProcess(@RequestParam(value = Params.GAME_ID, required = false) String gid,
            @RequestParam(value = Params.ANSWER_ID, required = false) String answer, Model model)
    {

        User currentUser = authenticator.getCurrentUser();
        GameSeries gameSeries = currentUser.getUserGameStorage().get(gid);

        //Если пост-запрос был послан по ошибке, когда игра решена
        if (isSolved(gameSeries))
        {
            model.addAttribute("wins", gameSeries.wonGamesCount());
            return "gamesolved";
        }

        gameSeries.input(answer);

        Game game = gameSeries.getGame();
        // вопрос, когда высчитывается состояние игры
        if (game.state() == GameState.VICTORY) {
            gameSeries.winOneGame();
        }
        if (game.state() == GameState.FAILURE) {
            gameSeries.loseOneGame();
        }

        setState(currentUser);

        if (isSolved(gameSeries))
        {
            model.addAttribute("wins", gameSeries.wonGamesCount());
            return "gamesolved";
        }

        model.addAttribute("description", game.getDescription());
        model.addAttribute("maxwins", gameSeries.maxWinsCount());
        model.addAttribute("wins", gameSeries.wonGamesCount());
        model.addAttribute("computerstate", game.computerOutput());
        model.addAttribute("computermessage", game.output());
        model.addAttribute("gamestate", game.state().getMessage());

        return "rungame";
    }

    private boolean isClosed(GameSeries gameSeries)
    {
        return gameSeries.getState() == GameSeriesState.CLOSED;
    }

    private boolean isSolved(GameSeries gameSeries)
    {
        return gameSeries.getState() == GameSeriesState.SOLVED;
    }

    private void setState(User currentUser)
    {
        dao.updateUser(currentUser);
    }

}
