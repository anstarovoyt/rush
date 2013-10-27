package ru.naumen.core.game;

/**
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameSeries
{
    private GameSeriesState state;
    private Game game;

    public GameSeries( Game game, GameSeriesState state )
    {
        this.game = game;
        this.state = state;
    }

    public static GameSeries openGame( Game game )
    {
        return new GameSeries( game, GameSeriesState.OPEN );
    }

    public static GameSeries closedGame( Game game )
    {
        return new GameSeries( game, GameSeriesState.CLOSED );
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
        return 0;
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
}
