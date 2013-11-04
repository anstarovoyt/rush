package ru.naumen.core.game;

import java.io.Serializable;

/**
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
            return "Ожидается ввод игрока";
        }
    },
    FAILURE
    {
        @Override
        public String getMessage()
        {
            return "Неудача";
        }
    },
    VICTORY
    {
        @Override
        public String getMessage()
        {
            return "Победа в партии";
        }
    };

    public abstract String getMessage();
}
