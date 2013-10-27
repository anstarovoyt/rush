package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOGame implements Game
{
    public static String formatter(String input)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                result.append(input.charAt(3 * i + j));
                if (j != 2)
                {
                    result.append("|");
                }
            }

            if (i != 2)
            {
                result.append("\n");
            }
        }
        return result.toString();
    }

    Boolean state[] = new Boolean[9];

    @Override
    public String getDescription()
    {
        return "You have a 3x3 board. You can put X symbols anywhere, one per turn. Your task is to put 3 X'es in a line.";
    }

    @Override
    public String getId()
    {
        return "xo";
    }

    @Override
    public void input(String userInput)
    {
    }

    @Override
    public String output()
    {
        return "O..|.X.|...";
    }

    @Override
    public GameState state()
    {
        return GameState.IN_PROGRESS;
    }
}
