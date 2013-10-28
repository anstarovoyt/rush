package ru.naumen.core.game;

/**
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameSeries
{
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

    public static GameSeries openGame( Game game, int maxWins, GameSeries... nextGames )
    {
        return new GameSeries( game, maxWins, nextGames, GameSeriesState.OPEN );
    }

    public static GameSeries closedGame( Game game, int maxWins )
    {
        return new GameSeries( game, maxWins, new GameSeries[]{}, GameSeriesState.CLOSED );
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
            for (GameSeries series : nextGames) {
                series.makeOpen();
            }
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
            makeSolved();
    }
}
