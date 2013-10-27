package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 27.10.13
 */
public class ForeverClosed implements Game
{
    @Override
    public String getId()
    {
        return "go";
    }

    @Override
    public String getDescription()
    {
        return "Это пример закрытой игры";
    }

    @Override
    public void input( String userInput )
    {
    }

    @Override
    public GameState state()
    {
        return GameState.VICTORY;
    }

    @Override
    public String output()
    {
        return "";
    }
}
