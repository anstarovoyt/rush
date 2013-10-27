package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class Doom implements Game
{
    GameState victory = GameState.IN_PROGRESS;

    @Override
    public String computerOutput()
    {
        return null;
    }

    @Override
    public String getDescription()
    {
        return "You have to fight evil martian monsters and find your way out from this awful maze!";
    }

    @Override
    public String getId() {
        return "dm";
    }

    @Override
    public void input( String userInput )
    {
        victory = "iddqd".equals( userInput ) ? GameState.VICTORY : GameState.FAILURE;
    }

    @Override
    public String output()
    {
        return victory == GameState.VICTORY ? "You are winner!" :  "You have been killed";
    }

    @Override
    public GameState state()
    {
        return victory;
    }
}
