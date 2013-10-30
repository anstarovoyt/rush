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


    /**
     *  R > S > P > R
     */
    public transient static final String RULES = "RSPR";

    public transient static final String ACCEPTED_STEP = "RSP";
    public transient static final String ORDERING = "PRSPPRRSS";
    public transient static final String ROCK = "R";
    public transient static final String PAPER = "P";
    public transient static final String SCISSORS = "S";


    int currentState = RND.nextInt(10);
    char currentUserStep;
    char currentComputerStep;
    GameState victory = GameState.IN_PROGRESS;

    @Override
    public String getShortName()
    {
        return "Камень-ножницы-бумага";
    }

    @Override
    public String getShortDescription()
    {
        return "Разве тут надо что-либо добавлять?";
    }

    @Override
    public String computerOutput()
    {
        return String.format("User %s, Computer %s");
    }

    @Override
    public String getDescription()
    {
        return "Камень, ножницы, бумага — популярная игра на руках, известная во многих странах мира.<br><br>"
                + "Камень побеждает ножницы («камень затупляет или ломает ножницы»)<br>"
                + "Ножницы побеждают бумагу («ножницы разрезают бумагу»)<br>"
                + "Бумага побеждает камень («бумага накрывает или заворачивает камень»)<br>"
                + "Пользовательский ввод: R - камень, S - ножницы, P - бумага<br>"
                + "Нужно для прохождения игры нужно одержать 50 побед подряд<br>" + "Только победа, и ничего другого!";
    }

    @Override
    public String getId()
    {
        return "rps";
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
        return null;
    }

    @Override
    public GameState state()
    {
        return victory;
    }

    @Override
    public Game resetState() {
        return this;
    }

    void setState(int indexOfState)
    {
        currentState = indexOfState;
    }

}
