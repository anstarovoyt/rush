package ru.naumen.core.game;

import static ru.naumen.core.game.GameSeries.closedGame;
import static ru.naumen.core.game.GameSeries.openGame;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.naumen.core.game.impl.*;

/**
 * Класс предоставляет интерфейс доступа к классам существующих игр
 * @author astarovoyt
 */
@Component
public class GameProvider
{
    public List<GameSeries> getNewGameList()
    {
        GameSeries closedGameExample = closedGame( new ForeverClosed(), 1 );
        return Arrays.asList(
                openGame( new Doom(), 1, closedGameExample ),
                openGame( new XOGame(), 50 ),
                openGame( new RPSGame(), 50 ),
                openGame( new Shtirlitz(), 1 ),
                openGame( new NameThatTune(), 1 ),
                openGame( new SpokGreeting(), 1 ),
                openGame( new Base64Game(), 1 ),
                openGame( new Fifteen(), 5 ),
                closedGameExample );
    }
}
