package ru.naumen.core.game.impl;

import org.apache.log4j.Logger;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 *
 * @author achernin
 * @since 30.10.13
 */
public class NameThatTune implements Game
{
    public static final Logger LOG = Logger.getLogger(NameThatTune.class);

    private static final long serialVersionUID = 1L;

    private static transient final String DESCRIPTION = "Угадай мелодию<br/><br/>" +

    "Fm6 &nbsp;&nbsp;  G7 &nbsp;&nbsp; Cm<br/>" + "C &nbsp;&nbsp; G7 &nbsp;&nbsp; C<br/>" + "F &nbsp;&nbsp; C<br/>"
            + "Am &nbsp;&nbsp; A7 &nbsp;&nbsp; Dm &nbsp;&nbsp; D#dim7<br/>" + "C &nbsp;&nbsp; G7 &nbsp;&nbsp; C<br/>";

    private GameState state = GameState.IN_PROGRESS;

    private static transient final String NAME_FORMAL = "Улыбка";

    private static transient final String NAME_UNFORMAL = "От улыбки станет всем светлей";

    @Override
    public String getDescription()
    {
        return DESCRIPTION;
    }

    @Override
    public String getId()
    {
        return "NameThatTune";
    }

    @Override
    public String getShortDescription()
    {
        return "Вам потребуется музыкальный слух, чтобы справиться с этим заданием!";
    }

    @Override
    public String getShortName()
    {
        return "Угадай мелодию";
    }

    @Override
    public String getStateRepresentation()
    {
        return null;
    }

    @Override
    public void input(String userInput)
    {
        String input = userInput.trim();

        LOG.debug("Input value " + input);

        if (NAME_FORMAL.equalsIgnoreCase(input) || NAME_UNFORMAL.equalsIgnoreCase(input))
        {
            state = GameState.VICTORY;
        }
        else
        {
            state = GameState.FAILURE;
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
        return state;
    }
}
