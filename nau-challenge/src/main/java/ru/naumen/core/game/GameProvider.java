package ru.naumen.core.game;

import org.springframework.stereotype.Component;
import ru.naumen.core.game.impl.Doom;
import ru.naumen.core.game.impl.XOGame;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: anstarovoyt
 * Date: 10/24/13
 * Time: 2:17 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class GameProvider {
    List<Game> games = Arrays.asList(new Doom(), new XOGame());


    public Game getGame(String gid, String userId)
    {
        for (Game game : games)
        {
            if (Objects.equals(game.getId(), gid))
            {
                return game;
            }
        }

        return null;
    }
}
