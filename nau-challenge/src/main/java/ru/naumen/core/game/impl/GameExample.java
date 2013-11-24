package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;
import ru.naumen.core.game.GameType;

/**
 * Example game
 * <br>
 * See git branches rush# for more info
 * <p/>
 * User: anstarovoyt
 * Date: 11/24/13
 * Time: 5:59 PM
 */
@GameType
public class GameExample implements Game
{
	public static final String ID = "ge";
	GameState state = GameState.IN_PROGRESS;
	String input = "";

	@Override
	public String getDescription()
	{
		return "Please enter 123";
	}

	@Override
	public String getId()
	{
		return ID;
	}

	@Override
	public String getShortDescription()
	{
		return "Simple game for example";
	}

	@Override
	public String getShortName()
	{
		return "Game example";
	}

	@Override
	public String getStateRepresentation()
	{
		return "input: " + input;
	}

	@Override
	public void input(String userInput)
	{
		input = userInput;
		if (userInput.equals("123"))
		{
			state = GameState.VICTORY;
		} else
		{
			state = GameState.FAILURE;
		}
	}

	@Override
	public String output()
	{
		if (state == GameState.FAILURE)
		{
			return "The enter '" + input + "' is incorrect!";
		}
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
