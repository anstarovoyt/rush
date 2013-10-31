package ru.naumen.mvc.controller;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.game.*;
import ru.naumen.core.info.Params;
import ru.naumen.core.storage.UserGameStorage;
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

import com.google.common.collect.Maps;

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

    @Inject
    GameProvider provider;

    ConcurrentMap<Long, ReentrantLock> locks = Maps.newConcurrentMap();

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

        model.addAttribute("gid", gid);

        setCommonParamsToModel(model, gameSeries);

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

        ReentrantLock lock = getLock(currentUser.getId());
        try
        {
            lock.lock();
            gameSeries.input(answer);

            changeSerialState(gameSeries, currentUser);

            setUser(currentUser);

            if (isSolved(gameSeries))
            {
                model.addAttribute("wins", gameSeries.wonGamesCount());
                return "gamesolved";
            }

            setCommonParamsToModel(model, gameSeries);
            return "rungame";
        }
        finally
        {
            lock.unlock();
        }
    }

    private void changeSerialState(GameSeries gameSeries, User currentUser)
    {
        Game game = gameSeries.getGame();
        // вопрос, когда высчитывается состояние игры
        if (game.state() == GameState.VICTORY)
        {
            gameSeries.winOneGame();

            openRelatedGames(gameSeries, currentUser);

        }
        if (game.state() == GameState.FAILURE)
        {
            gameSeries.loseOneGame();
        }
    }

    private ReentrantLock getLock(long userId)
    {
        ReentrantLock result = locks.get(userId);

        if (result == null)
        {
            locks.putIfAbsent(userId, new ReentrantLock());
        }
        return locks.get(userId);
    }

    private boolean isClosed(GameSeries gameSeries)
    {
        return gameSeries.getState() == GameSeriesState.CLOSED;
    }

    private boolean isSolved(GameSeries gameSeries)
    {
        return gameSeries.getState() == GameSeriesState.SOLVED;
    }

    private void openRelatedGames(GameSeries gameSeries, User currentUser)
    {
        List<String> relatedGames = provider.getRelatedClosedGameIds(gameSeries.getId());

        UserGameStorage storage = currentUser.getUserGameStorage();

        for (String relatedId : relatedGames)
        {
            GameSeries relatedSeries = storage.get(relatedId);
            relatedSeries.makeOpen();
        }
    }

    private void setCommonParamsToModel(Model model, GameSeries gameSeries)
    {
        Game game = gameSeries.getGame();

        model.addAttribute("description", game.getDescription());
        model.addAttribute("maxwins", gameSeries.maxWinsCount());
        model.addAttribute("wins", gameSeries.wonGamesCount());
        model.addAttribute("computerstate", game.getStateRepresentation());
        model.addAttribute("computermessage", game.output());
        model.addAttribute("gamestate", game.state().getMessage());
    }

    private void setUser(User currentUser)
    {
        dao.saveUser(currentUser);
    }
}
