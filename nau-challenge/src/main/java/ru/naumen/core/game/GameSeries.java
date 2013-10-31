package ru.naumen.core.game;

import java.io.Serializable;

/**
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameSeries implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static GameSeries closedGame(Game game, int maxWins)
    {
        return new GameSeries(game, maxWins, GameSeriesState.CLOSED);
    }

    public static GameSeries openGame(Game game, int maxWins)
    {
        return new GameSeries(game, maxWins, GameSeriesState.OPEN);
    }

    private int gamesWon = 0;
    private GameSeriesState state;

    private Game game;

    private int maxWins;

    public GameSeries(Game game, int maxWins, GameSeriesState state)
    {
        this.game = game;
        this.maxWins = maxWins;
        this.state = state;
    }

    public Game getGame()
    {
        return game;
    }

    public String getId()
    {
        return game.getId();
    }

    public GameSeriesState getState()
    {
        return state;
    }

    public void input(String answer)
    {

        if (game.state() == GameState.FAILURE || game.state() == GameState.VICTORY)
        {
            resetGame();
        }

        game.input(answer);
    }

    public void loseOneGame()
    {
        gamesWon = 0;
    }

    public void makeOpen()
    {
        if (state == GameSeriesState.CLOSED)
        {
            state = GameSeriesState.OPEN;
        }
    }

    public void makeSolved()
    {
        if (state == GameSeriesState.OPEN)
        {
            state = GameSeriesState.SOLVED;

        }
    }

    public int maxWinsCount()
    {
        return maxWins;
    }

    public void resetGame()
    {
        game = game.resetState();
    }

    public void winOneGame()
    {
        gamesWon++;
        if (gamesWon >= maxWins)
        {
            makeSolved();
        }
    }

    public int wonGamesCount()
    {
        return gamesWon;
    }
}
