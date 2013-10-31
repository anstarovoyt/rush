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
public class RatingController {
    
    @Inject
    UserDAO userDAO;
    
    @RequestMapping(value = "/super-sercret-rating", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String gameProcess(Model model)
    {
        List<User> allUsers = userDAO.loadAll(); 
        List<RatingRow> rows = new ArrayList<>();
        for(User user : allUsers) {
            Collection<GameSeries> games = user.getUserGameStorage().getAll();
            int solvedCount = 0;
            for(GameSeries game : games) {
                if(game.getState() == GameSeriesState.SOLVED) {
                    solvedCount++;
                }
            }
            rows.add(new RatingRow(user.getFio(), solvedCount, null));
        }
        Collections.sort(rows, new Comparator<RatingRow>() {
            @Override
            public int compare(RatingRow o1, RatingRow o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        model.addAttribute("rows", rows);
        return "rating";
    }
}
