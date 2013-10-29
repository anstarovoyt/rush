package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 27.10.13
 */
public class ForeverClosed implements Game
{

    private static final long serialVersionUID = 4738667777317130283L;

    @Override
    public String computerOutput()
    {
        return "";
    }

    @Override
    public String getDescription()
    {
        return "Это пример закрытой игры";
    }

    @Override
    public String getId()
    {
        return "go";
    }

    @Override
    public void input( String userInput )
    {
    }

    @Override
    public String output()
    {
        return "";
    }

    @Override
    public GameState state()
    {
        return GameState.VICTORY;
    }
}
