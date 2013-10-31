package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class Doom implements Game
{
    private static final String DESCRIPTION = "Есть один универсальный способ пройти DOOM. Какой?";

    private static final long serialVersionUID = 1L;

    /**
     * state of game
     * @serial
     */
    GameState victory = GameState.IN_PROGRESS;

    @Override
    public String getDescription()
    {
        return DESCRIPTION;
    }

    @Override
    public String getId()
    {
        return "dm";
    }

    @Override
    public String getShortDescription()
    {
        return "Классический шутер";
    }

    @Override
    public String getShortName()
    {
        return "Doom";
    }

    @Override
    public String getStateRepresentation()
    {
        return null;
    }

    @Override
    public void input(String userInput)
    {
        victory = "iddqd".equalsIgnoreCase(userInput) ? GameState.VICTORY : GameState.IN_PROGRESS;
    }

    @Override
    public String output()
    {
        return victory == GameState.VICTORY ? "You are winner!" : "";
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
