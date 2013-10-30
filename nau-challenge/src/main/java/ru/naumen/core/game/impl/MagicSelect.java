package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;
import ru.naumen.core.game.impl.magicselect.TemporarySchemaProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author achernin
 * @since 30.10.13
 */
public class MagicSelect implements Game
{

    private static final transient Logger log = Logger.getLogger(MagicSelect.class.getName());

    @Override
    public String computerOutput()
    {
        return null;
    }

    private static final transient String DESCRIPTION =
            "При каждом запуске игра делает запрос к реляционной базе данных по миру сериала СтарТрэк <br/>" +
            "select start_year from klingon_leader where leader = '?' <br/>" +
            "Игрок выигрывает в игру, если результатом выполнения запроса будет год, в который началось правление канцлера Азетбура.<br/>";

    @Override
    public String getDescription()
    {
        return DESCRIPTION;
    }

    @Override
    public String getId()
    {
        return "MagicSelect";
    }

    private String prev_result;

    private static transient final String YEAR = "2293";


    @Override
    public void input(String userInput)
    {
        try {
            prev_result = TemporarySchemaProvider.execute(userInput);
        } catch (Exception e)
        {
            prev_result = "Uups... Something wrong!!!";
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        if (YEAR.equalsIgnoreCase(userInput))
        {
            state = GameState.VICTORY;
        } else {
            state = GameState.FAILURE;
        }
    }

    @Override
    public String output()
    {
        return prev_result;
    }

    private GameState state = GameState.IN_PROGRESS;

    @Override
    public GameState state()
    {
        return state;
    }
}