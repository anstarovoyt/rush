package ru.naumen.mvc.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.game.GameSeries;
import ru.naumen.model.dao.UserDAO;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Controller for page "games"
 *
 * @author Andrey Hitrin
 * @since 30.10.13
 */
@Controller
public class GamesListController
{
	public static final String JSP_ALL_GAMES = "allgames";
	@Inject
    Authenticator authenticator;

    @Inject
    UserDAO dao;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public String gamesList(Model model)
    {
        Collection<GameSeries> games = authenticator.getCurrentUser().getUserGameStorage().getAll();
        List<GameSeries> gameIds = Lists.newArrayList(games);
        model.addAttribute("gameIds", gameIds);
        return JSP_ALL_GAMES;
    }
}
