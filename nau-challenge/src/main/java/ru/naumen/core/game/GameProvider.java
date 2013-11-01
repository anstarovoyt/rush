package ru.naumen.core.game;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import ru.naumen.core.game.impl.*;

import java.util.Arrays;
import java.util.List;

import static ru.naumen.core.game.GameSeries.closedGame;
import static ru.naumen.core.game.GameSeries.openGame;

/**
 * Класс предоставляет интерфейс доступа к классам существующих игр
 * @author astarovoyt
 */
@Component
public class GameProvider
{
    //@formatter:off
    public ImmutableMap<String, List<String>> relations =
            ImmutableMap.<String, List<String>>
                of(Doom.ID, Lists.newArrayList(Redo.ID, MagicSelect.ID),
                   XOGame.ID, Lists.newArrayList(Fifteen.ID),
                   Base64Game.ID, Lists.newArrayList(Shtirlitz.ID)
                  );

    public List<GameSeries> getNewGameList()
    {
        return Arrays.asList(
                openGame(new Doom(), 1),
                openGame(new XOGame(), 100),
                openGame(new RPSGame(), 100),
                openGame(new Base64Game(), 1),
                openGame(new NameThatTune(), 1),
                openGame(new SpokGreeting(), 1),
                openGame(new Diff(), 1),
                openGame(new Ktulhu(), 1),
                openGame(new TrollLife(), 1),
                openGame(new Befunge(), 1),
                closedGame(new Fifteen(), 50),
                closedGame(new Shtirlitz(), 1),
                closedGame(new Redo(), 1),
                closedGame(new MagicSelect(), 1),
                openGame(new Console(), 1));
        //@formatter:on

    }

    /**
     * Тут нужно задать маппинг открытия игр по зависмостям
     *
     */
    public List<String> getRelatedClosedGameIds(String gameId)
    {
        return relations.containsKey(gameId) ? relations.get(gameId) : Lists.<String> newArrayList();
    }
}
