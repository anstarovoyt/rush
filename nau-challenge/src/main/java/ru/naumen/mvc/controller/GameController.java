package ru.naumen.mvc.controller;

import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
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

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Controller for page "Game"
 * <p/>
 * User: anstarovoyt
 * Date: 10/24/13
 * Time: 12:30 AM
 */
@Controller
public class GameController
{
	public static final Logger LOG = Logger.getLogger(GameController.class);
	public static final String JSP_ALL_GAMES = GamesListController.JSP_ALL_GAMES;
	public static final String JSP_GAME = "rungame";
	public static final String JSP_GAME_SOLVED = "gamesolved";
	public static final String JSP_GAME_CLOSED = "gameclosed";

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

		User currentUser = authenticator.getCurrentUser();
		GameSeries gameSeries = currentUser.getUserGameStorage().get(gid);

		//введен неправильный gid
		if (gameSeries == null)
		{
			LOG.debug("User " + currentUser + " try to get game that not exits" + gid);
			return JSP_ALL_GAMES;
		}

		if (isClosed(gameSeries))
		{
			return JSP_GAME_CLOSED;
		}
		if (isSolved(gameSeries))
		{
			model.addAttribute("wins", gameSeries.wonGamesCount());
			return JSP_GAME_SOLVED;
		}

		model.addAttribute("gid", gid);

		setCommonParamsToModel(model, gameSeries);

		return JSP_GAME;
	}

	@RequestMapping(value = "/game", method = RequestMethod.POST)
	public String gameProcess(@RequestParam(value = Params.GAME_ID, required = false) String gid,
							  @RequestParam(value = Params.ANSWER_ID, required = false) String answer, Model model)
	{
		User currentUser = authenticator.getCurrentUser();
		GameSeries gameSeries = currentUser.getUserGameStorage().get(gid);

		//incorrect gid
		if (gameSeries == null)
		{
			LOG.debug("User " + currentUser + " try to get game that not exits" + gid);
			return JSP_ALL_GAMES;
		}

		//Already solved
		if (isSolved(gameSeries))
		{
			LOG.debug("User " + currentUser + " do post for solved task " + gameSeries.getId());
			model.addAttribute("wins", gameSeries.wonGamesCount());
			return JSP_GAME_SOLVED;
		}

		//execute game per user
		//we store info as serialized object and cannot solve conflicts
		ReentrantLock lock = getLock(currentUser.getId());
		try
		{
			lock.lock();
			gameSeries.input(answer);

			changeSeriesState(gameSeries, currentUser);

			if (isSolved(gameSeries))
			{
				LOG.info("User " + currentUser + " solved task " + gameSeries.getId());
				openRelatedGames(gameSeries, currentUser);
			}

			setUser(currentUser);

			if (isSolved(gameSeries))
			{
				model.addAttribute("wins", gameSeries.wonGamesCount());
				return JSP_GAME_SOLVED;
			}

			setCommonParamsToModel(model, gameSeries);
			return JSP_GAME;
		} finally
		{
			lock.unlock();
		}
	}

	private void changeSeriesState(GameSeries gameSeries, User currentUser)
	{
		Game game = gameSeries.getGame();

		if (game.state() == GameState.VICTORY)
		{
			gameSeries.winOneGame();
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
			LOG.info("User " + currentUser + " open game " + relatedId);
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
		model.addAttribute("enumstate", game.state());
	}

	private void setUser(User currentUser)
	{
		dao.saveUser(currentUser);
	}
}
