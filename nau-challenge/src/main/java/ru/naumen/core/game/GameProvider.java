package ru.naumen.core.game;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.naumen.core.game.impl.Doom;
import ru.naumen.core.game.impl.XOGame;

/**
 * Класс предоставляет интерфейс доступа к классам существующих игр
 * @author astarovoyt
 */
@Component
public class GameProvider
{
    public List<Game> getNewGameList()
    {
        return Arrays.asList( new Doom(), new XOGame() );
    }
}
