package ru.naumen.core.game.impl;

import java.util.Set;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

import com.google.common.collect.Sets;

/**
 * @author astarovoyt
 *
 */
public class Diff implements Game
{
    private static final long serialVersionUID = 1L;

    public static final Set<Integer> COORDINATES = Sets.newHashSet(150, 80, 100, 20, 160, 10);
    public static final Set<Integer> COORDINATES_SHIFT = Sets.newHashSet(151, 81, 101, 21, 161, 11);
    public static final Set<Integer> COORDINATES_SHIFT_2 = Sets.newHashSet(149, 79, 99, 19, 159, 9);

    /**
     * state of game
     * @serial
     */
    GameState victory = GameState.IN_PROGRESS;

    @Override
    public String getDescription()
    {
        return "Найди отличия <br> <img src='/resources/img/diff.png' /> <img src='/resources/img/diff2.png' />";
    }

    @Override
    public String getId()
    {
        return "df";
    }

    @Override
    public String getShortDescription()
    {
        return "Классическая игра 'найди несколько отличий'";
    }

    @Override
    public String getShortName()
    {
        return "Difference";
    }

    @Override
    public String getStateRepresentation()
    {
        return null;
    }

    @Override
    public void input(String userInput)
    {
        if (isAnswer(userInput))
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

    private Integer getNumber(StringBuilder currentNumber)
    {
        try
        {
            return Integer.valueOf(currentNumber.toString());

        }
        catch (Exception e)
        {
            //Do nothing
        }

        return null;
    }

    private boolean isAnswer(String userInput)
    {
        //Входную строку подрезаем, чтобы не крутиться черти сколько
        String substring = userInput.length() > 100 ? userInput.substring(0, 100) : userInput;
        Set<Integer> parsedNumbers = stringToSetNumbers(substring);
        return parsedNumbers.equals(COORDINATES) || parsedNumbers.equals(COORDINATES_SHIFT)
                || parsedNumbers.equals(COORDINATES_SHIFT_2);
    }

    private Set<Integer> stringToSetNumbers(String userInput)
    {
        Set<Integer> result = Sets.newHashSet();
        StringBuilder currentNumber = new StringBuilder();
        for (int index = 0; index < userInput.length(); index++)
        {
            char c = userInput.charAt(index);
            if (Character.isDigit(c))
            {
                currentNumber.append(c);
            }
            else
            {
                if (currentNumber.length() > 0)
                {
                    Integer number = getNumber(currentNumber);
                    result.add(number);
                    currentNumber = new StringBuilder();
                }
            }
        }

        if (currentNumber.length() > 0)
        {
            Integer number = getNumber(currentNumber);
            result.add(number);
        }

        return result;
    }

}
