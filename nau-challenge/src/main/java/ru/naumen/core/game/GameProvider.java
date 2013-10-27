package ru.naumen.core.game;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.naumen.core.game.impl.Doom;
import ru.naumen.core.game.impl.XOGame;

import com.google.common.collect.Lists;

/**
 * Класс предоставляет интерфейс доступа к классам существующих игр
 * @author astarovoyt
 */
@Component
public class GameProvider
{
    List<Class<? extends Game>> games = Arrays.asList(Doom.class, XOGame.class);


    public List<Game> getNewGameList()
    {
        List<Game> result = Lists.newArrayList();

        for (Class<? extends Game> game : games)
        {
            try
            {
                result.add(game.newInstance());
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
