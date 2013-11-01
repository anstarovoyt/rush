package ru.naumen.mvc.controller;

import java.util.*;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.naumen.core.game.GameSeries;
import ru.naumen.core.game.GameSeriesState;
import ru.naumen.core.rating.RatingRow;
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

@Controller
public class RatingController
{

    @Inject
    UserDAO userDAO;

    @RequestMapping(value = "/super-secret-rating", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String gameProcess(Model model)
    {
        List<RatingRow> rows = getRating(true);
        model.addAttribute("rows", rows);
        return "rating";
    }

    @RequestMapping(value = "/secret-rating", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String gameProcessNonSecret(Model model)
    {
        List<RatingRow> rows = getRating(false);
        model.addAttribute("rows", rows);
        return "rating";
    }

    private List<RatingRow> getRating(boolean addEmpty)
    {
        List<User> allUsers = userDAO.loadAll();
        List<RatingRow> rows = new ArrayList<>();
        for (User user : allUsers)
        {
            Collection<GameSeries> games = user.getUserGameStorage().getAll();
            int solvedCount = 0;
            long maxDate = 0;
            for (GameSeries game : games)
            {
                if (game.getState() == GameSeriesState.SOLVED)
                {
                    if (game.getWinDate() == null)
                    {
                        throw new IllegalArgumentException(
                                "Solved date should contain date of winning, maybe you need recreate your db");
                    }
                    solvedCount++;
                    maxDate = Math.max(maxDate, game.getWinDate().getTime());
                }
            }

            if (addEmpty || solvedCount > 0)
            {
                rows.add(new RatingRow(user.getFio(), solvedCount, maxDate));
            }
        }
        Collections.sort(rows, new Comparator<RatingRow>()
        {
            @Override
            public int compare(RatingRow o1, RatingRow o2)
            {
                return o2.getScore() - o1.getScore();
            }
        });
        return rows;
    }
}
