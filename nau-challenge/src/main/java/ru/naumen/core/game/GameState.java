package ru.naumen.core.game;

import java.io.Serializable;

/**
 * Inner state of game
 *
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public enum GameState implements Serializable
{

    IN_PROGRESS
    {
        @Override
        public String getMessage()
        {
            return "Waiting user input";
        }
    },
    FAILURE
    {
        @Override
        public String getMessage()
        {
            return "Failure";
        }
    },
    VICTORY
    {
        @Override
        public String getMessage()
        {
            return "Victory in the party";
        }
    };

    public abstract String getMessage();
}
