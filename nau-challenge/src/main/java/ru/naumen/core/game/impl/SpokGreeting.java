package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 *
 * @author achernin
 * @since 30.10.13
 */
public class SpokGreeting implements Game
{

    private static final long serialVersionUID = 1L;

    @Override
    public String getShortName()
    {
        return "Приветствие Спока";
    }

    @Override
    public String getShortDescription()
    {
        return "Будь Спок";
    }

    @Override
    public String computerOutput()
    {
        return null;
    }

    private static transient final String DESCRIPTION =
            "Переведи приветствие Спока на английский язык!<br/><br/>" +

                    "<img src=\"/resources/custom/SpokGreeting.jpg\" padding=0 /><br/>";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getId() {
        return "SpokGreeting";
    }

    private static transient final String MSG = "Devel Camp Challenge";
    private static transient final String MSG_VARIANT = "Develop Camp Challenge";

    @Override
    public void input(String userInput)
    {
        if (MSG.equalsIgnoreCase(userInput) || MSG_VARIANT.equalsIgnoreCase(userInput))
        {
            state = GameState.VICTORY;
        } else {
            state = GameState.FAILURE;
        }
    }

    @Override
    public String output()
    {
        return null;
    }

    private GameState state = GameState.IN_PROGRESS;

    @Override
    public GameState state()
    {
        return state;
    }
}
