package ru.naumen.core.game;

import java.io.Serializable;

/**
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameSeries implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static GameSeries closedGame( Game game, int maxWins )
    {
        return new GameSeries( game, maxWins, new GameSeries[]{}, GameSeriesState.CLOSED );
    }
    public static GameSeries openGame( Game game, int maxWins, GameSeries... nextGames )
    {
        return new GameSeries( game, maxWins, nextGames, GameSeriesState.OPEN );
    }
    private final GameSeries[] nextGames;
    private int gamesWon = 0;
    private GameSeriesState state;

    private Game game;

    private int maxWins;

    public GameSeries( Game game, int maxWins, GameSeries[] nextGames, GameSeriesState state )
    {
        this.game = game;
        this.maxWins = maxWins;
        this.state = state;
        this.nextGames = nextGames;
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

    public void loseOneGame()
    {
        gamesWon = 0;
    }

    public void makeOpen()
    {
        if (state == GameSeriesState.CLOSED) {
            state = GameSeriesState.OPEN;
        }
    }

    public void makeSolved()
    {
        if (state == GameSeriesState.OPEN) {
            state = GameSeriesState.SOLVED;
            for (GameSeries series : nextGames) {
                series.makeOpen();
            }
        }
    }

    public int maxWinsCount()
    {
        return maxWins;
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
