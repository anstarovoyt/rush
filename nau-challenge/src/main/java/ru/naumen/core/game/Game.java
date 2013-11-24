package ru.naumen.core.game;

import java.io.Serializable;

/**
 * Game interface
 * A new game class must implements Game interface
 * and must be marked with @GameType annotation
 *
 * @author Andrey Hitrin, astarovoyt
 * @since 21.10.13
 */
public interface Game extends Serializable
{
	/**
	 * Here must be detailed game description with all rules
	 * and specified user input
	 */
	String getDescription();

	/**
	 * Unique game id
	 * <p/>
	 * Example: g1, g2 and so on
	 */
	String getId();

	/**
	 * Short game description
	 * <p/>
	 * Info is displayed in game list page
	 */
	String getShortDescription();

	/**
	 * Game name (is used for game list page and game page)
	 */
	String getShortName();

	/**
	 * Current computer state
	 * <p/>
	 * For example, in Tic-tac-toe it can be board
	 */
	String getStateRepresentation();

	void input(String userInput);

	/**
	 * A little message marked as important
	 * <p/>
	 * For example, it can be message "Input is incorrect"
	 */
	String output();

	/**
	 * Game series called method between win and next input
	 * <p/>
	 * If game-class is stateless impl can be : "return this;"
	 */
	Game resetState();

	/**
	 * Current game state
	 */
	GameState state();
}
