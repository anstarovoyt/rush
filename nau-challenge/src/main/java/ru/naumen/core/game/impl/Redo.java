package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;
import ru.naumen.core.game.GameType;

/**
 * @author astarovoyt
 *
 */
@GameType(blockedBy = Doom.ID)
public class Redo implements Game
{

    private static final long serialVersionUID = 1L;

    public static final String ID = "rd";

    GameState victory = GameState.IN_PROGRESS;

    @Override
    public String getDescription()
    {
        return "У админа горе: накрылась база данных и все что от нее осталось это очень странный Redo-log.<br>"
                + "Админ знает, что в таблице оставалось всего одно число, но увы, не помнит какое.<br>"
                + "Помогите админу распарсить лог и спасти свою жизнь! ";
    }

    @Override
    public String getId()
    {
        return ID;
    }

    @Override
    public String getShortDescription()
    {
        return "Помогите админу спасти свою жизнь";
    }

    @Override
    public String getShortName()
    {
        return "Горе админ";
    }

    @Override
    public String getStateRepresentation()
    {
        return "<a href='/resources/txt/redo.txt'>redo.log</a>";
    }

    @Override
    public void input(String userInput)
    {
        if ("1131491886".equals(userInput))
        {
            victory = GameState.VICTORY;
        }
        else
        {
            victory = GameState.FAILURE;
        }
    }

    @Override
    public String output()
    {
        return null;
    }

    @Override
    public Game resetState()
    {
        return this;
    }

    @Override
    public GameState state()
    {
        return victory;
    }

}
