package ru.naumen.core.game;

/**
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameSeries
{
    private int gamesWon = 0;
    private GameSeriesState state;
    private Game game;
    private int maxWins;

    public GameSeries( Game game, int maxWins, GameSeriesState state )
    {
        this.game = game;
        this.maxWins = maxWins;
        this.state = state;
    }

    public static GameSeries openGame( Game game, int maxWins )
    {
        return new GameSeries( game, maxWins, GameSeriesState.OPEN );
    }

    public static GameSeries closedGame( Game game )
    {
        return new GameSeries( game, 1, GameSeriesState.CLOSED );
    }

    public GameSeriesState getState()
    {
        return state;
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
        }
    }

    public int wonGamesCount()
    {
        return gamesWon;
    }

    public Game getGame()
    {
        if (state == GameSeriesState.OPEN)
            return game;
        return null;
    }

    public String getId()
    {
        return game.getId();
    }

    public int maxWinsCount()
    {
        return maxWins;
    }

    public void winOneGame()
    {
        gamesWon++;
        if (gamesWon >= maxWins)
            state = GameSeriesState.SOLVED;
    }
}
