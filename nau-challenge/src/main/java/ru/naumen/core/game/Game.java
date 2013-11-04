package ru.naumen.core.game;

import java.io.Serializable;

/**
 *
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public interface Game extends Serializable
{
    String getDescription();

    String getId();

    String getShortDescription();

    String getShortName();

    String getStateRepresentation();

    void input(String userInput);

    String output();

    Game resetState();

    GameState state();
}
