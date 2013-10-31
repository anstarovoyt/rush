package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

import java.util.Random;

/**
 * Камень-ножницы-бумага
 * Rock-paper-scissors
 *
 *
 * @author astarovoyt
 *
 */
public class RPSGame implements Game
{
    public transient static final long serialVersionUID = 1L;
    public transient static final Random RND = new Random();

    public static final String ID = "rps";

    /**
     *  R > S > P > R
     */
    public transient static final String RULES = "RSPR";

    public transient static final String ACCEPTED_STEP = "RSP";
    public transient static final String ORDERING = "PRSPPRRSS";
    public transient static final String ROCK = "R";
    public transient static final String PAPER = "P";
    public transient static final String SCISSORS = "S";

    private static String getRPS(Character step)
    {
        if (step.equals('R'))
        {
            return "камень";
        }
        if (step.equals('P'))
        {
            return "бумага";
        }
        if (step.equals('S'))
        {
            return "ножницы";
        }
        return null;
    }
    int currentState = 5;
    Character currentUserStep;
    Character currentComputerStep;

    GameState victory = GameState.IN_PROGRESS;

    transient boolean isInputIncorrect;

    @Override
    public String getDescription()
    {
        return "Камень, ножницы, бумага — популярная игра на руках, известная во многих странах мира.<br><br>"
                + "Пользовательский ввод: R - камень, S - ножницы, P - бумага<br>"
                + "Для прохождения игры нужно одержать 100 побед подряд<br>" + "Только победа, и ничего другого!";
    }

    @Override
    public String getId()
    {
        return ID;
    }

    @Override
    public String getShortDescription()
    {
        return "Разве тут надо что-либо добавлять?";
    }

    @Override
    public String getShortName()
    {
        return "Камень-ножницы-бумага";
    }

    @Override
    public String getStateRepresentation()
    {
        if (currentUserStep != null && currentComputerStep != null)
        {
            return String.format("Игрок — %s, Компьютер — %s", getRPS(currentUserStep), getRPS(currentComputerStep));
        }
        return null;
    }

    @Override
    public void input(String userInput)
    {
        try
        {
            //TODO прекондишены
            if (userInput.length() != 1 || ACCEPTED_STEP.indexOf(userInput) < 0)
            {
                throw new IllegalArgumentException();
            }

            currentState++;
            currentUserStep = userInput.charAt(0);
            currentComputerStep = ORDERING.charAt((currentState % ORDERING.length()));

            if (isUserWin(currentUserStep, currentComputerStep))
            {
                victory = GameState.VICTORY;
            }
            else
            {
                victory = GameState.FAILURE;
            }

        }
        catch (IllegalArgumentException e)
        {
            isInputIncorrect = true;
            victory = GameState.IN_PROGRESS;
        }
    }

    public boolean isUserWin(char userStep, char computerStep)
    {
        int indexOfUserStep = RULES.indexOf(userStep);

        return (RULES.charAt(indexOfUserStep + 1) == computerStep);
    }

    @Override
    public String output()
    {
        return isInputIncorrect ? "Некорретный ввод" : null;
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

    void setState(int indexOfState)
    {
        currentState = indexOfState;
    }

}
