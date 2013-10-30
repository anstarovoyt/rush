package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 *
 * @author achernin
 * @since 30.10.13
 */
public class NameThatTune implements Game
{

    private static final long serialVersionUID = 1L;

    @Override
    public String computerOutput()
    {
        return null;
    }

    private static transient final String DESCRIPTION =
            "Угадай мелодию<br/><br/>" +

                    "Fm6 &nbsp;&nbsp;  G7 &nbsp;&nbsp; Cm<br/>" +
                    "C &nbsp;&nbsp; G7 &nbsp;&nbsp; C<br/>" +
                    "F &nbsp;&nbsp; C<br/>" +
                    "Am &nbsp;&nbsp; A7 &nbsp;&nbsp; Dm &nbsp;&nbsp; D#dim7<br/>" +
                    "C &nbsp;&nbsp; G7 &nbsp;&nbsp; C<br/>";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getId() {
        return "NameThatTune";
    }

    private static transient final String NAME_FORMAL = "Улыбка";
    private static transient final String NAME_UNFORMAL = "От улыбки станет всем светлей";

    @Override
    public void input(String userInput)
    {
        if (NAME_FORMAL.equalsIgnoreCase(userInput) || NAME_UNFORMAL.equalsIgnoreCase(userInput))
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
