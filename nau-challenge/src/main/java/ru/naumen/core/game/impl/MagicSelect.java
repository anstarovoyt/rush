package ru.naumen.core.game.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;
import ru.naumen.core.game.impl.magicselect.TemporarySchemaProvider;

/**
 *
 * @author achernin
 * @since 30.10.13
 */
public class MagicSelect implements Game
{
    private static final long serialVersionUID = 1L;

    public static final String ID = "ms";

    private static final transient Logger log = Logger.getLogger(MagicSelect.class.getName());

    private static final transient String DESCRIPTION =
            "В игре имеется реляционная база данных по миру сериала СтарТрэк <br/>" +
            "В базе создана таблица с информацией по лидерам одной из галактических рас - Клингонам <br/>" +
            "  CREATE TABLE klingon_leader ( leader varchar(100), start_year int ) <br/>" +
            "  leader - имя лидера <br/>" +
            "  start_year - год по земному исчислению, начиная с которого лидер стоял во главе Клингонской Империи. <br/>" +
            "Во время каждого запуска программа пытается выполнить запрос <br/>" +
            "  select start_year from klingon_leader where leader = '?' <br/>" +
            "и получить год начала правления канцлера Азетбура (Azethbur) - 2293 г. <br/>" +
            "Но что-то идет не так и игре нужна твоя помощь. <br/>" +
            "Помоги игре правильно получить нужную дату! <br/>"
            + "(sql-запрос - значение, которое ты вводишь, будет подставлено вместо знака вопрос!)";


    private String prev_result;

    private static transient final String YEAR = "2293";

    private GameState state = GameState.IN_PROGRESS;

    @Override
    public String getDescription()
    {
        return DESCRIPTION;
    }

    @Override
    public String getId()
    {
        return ID;
    }

    @Override
    public String getShortDescription()
    {
        return "Игра по миру Star Trek";
    }

    @Override
    public String getShortName()
    {
        return "Star trek";
    }

    @Override
    public String getStateRepresentation()
    {
        return prev_result;
    }

    @Override
    public void input(String userInput)
    {
        try
        {
            prev_result = TemporarySchemaProvider.execute(userInput);
        }
        catch (Exception e)
        {
            prev_result = "Uups... Something wrong!!!";
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        if (YEAR.equalsIgnoreCase(userInput))
        {
            state = GameState.VICTORY;
        }
        else
        {
            state = GameState.FAILURE;
        }
    }

    @Override
    public String output()
    {
        return getStateRepresentation();
    }

    @Override
    public Game resetState()
    {
        state = GameState.IN_PROGRESS;
        return this;
    }

    @Override
    public GameState state()
    {
        return state;
    }
}