package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOGame implements Game
{
    public static final char COMPUTER_CHAR = 'O';
    public static final char USER_CHAR = 'X';
    public static final char UNUSED_CHAR = '*';


    /**
     *  У нас есть набор отображаемых символов
     *  XO* -> мы должны уметь представлять их в виде некоторого вывода
     */
    public static String format(char[] input)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                result.append(input[(3 * i + j)]);
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

    public static String unformat(String input)
    {
        return input.replace("\n", "")
                    .replace("|", "");
    }



    char state[] = { '*', '*', '*', '*', '*', '*', '*', '*', '*' };

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

    /**
     *
     * 1 | 2 | 3
     * _________
     * 4 | 5 | 6
     * _________
     * 7 | 8 | 9
     *
     */
    @Override
    public void input(String userInput)
    {
        try
        {
            int value = Integer.parseInt(userInput);
            if(value < 1 || value > 9 || state[value] != UNUSED_CHAR)
            {
                throw new IllegalArgumentException();
            }

            state[value] = USER_CHAR;

        }
        catch (IllegalArgumentException e)
        {
            //Некорректный ввод
        }
    }

    @Override
    public String output()
    {
        return format(state);
    }

    @Override
    public GameState state()
    {
        return GameState.IN_PROGRESS;
    }
}
