package ru.naumen.core.game;

import static ru.naumen.core.game.GameSeries.closedGame;
import static ru.naumen.core.game.GameSeries.openGame;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import ru.naumen.core.game.impl.Base64Game;
import ru.naumen.core.game.impl.Diff;
import ru.naumen.core.game.impl.Doom;
import ru.naumen.core.game.impl.Fifteen;
import ru.naumen.core.game.impl.Ktulhu;
import ru.naumen.core.game.impl.MagicSelect;
import ru.naumen.core.game.impl.NameThatTune;
import ru.naumen.core.game.impl.RPSGame;
import ru.naumen.core.game.impl.Redo;
import ru.naumen.core.game.impl.Shtirlitz;
import ru.naumen.core.game.impl.SpokGreeting;
import ru.naumen.core.game.impl.TrollLife;
import ru.naumen.core.game.impl.XOGame;

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
                of(Doom.ID, Lists.newArrayList(Redo.ID, MagicSelect.ID));

    public List<GameSeries> getNewGameList()
    {
        return Arrays.asList(
                openGame(new Doom(), 1),
                openGame(new XOGame(), 100),
                openGame(new RPSGame(), 100),
                openGame(new Shtirlitz(), 1),
                openGame(new NameThatTune(), 1),
                openGame(new SpokGreeting(), 1),
                openGame(new Base64Game(), 1),
                openGame(new Diff(), 1),
                openGame(new Fifteen(), 5),
                openGame(new Ktulhu(), 1),
                openGame(new TrollLife(), 1),
                closedGame(new Redo(), 1),
                closedGame(new MagicSelect(), 1));
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
