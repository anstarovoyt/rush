package ru.naumen.core.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

/**
 * Серия игр может быть закрыта, открыта, решена. Если серия открыта, то у неё есть
 * счётчик побед.
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameSeriesTest
{
    Game game = mock(Game.class);
    GameSeries closedGame = GameSeries.closedGame( game );
    GameSeries openGame = GameSeries.openGame( game );

    @Test
    public void gameSeriesCanBeOpenAtStart() {
        assertThat( openGame.getState(), is(GameSeriesState.OPEN));
    }

    @Test
    public void gameSeriesCanBeClosedAtStart() {
        assertThat( closedGame.getState(), is(GameSeriesState.CLOSED) );
    }

    @Test
    public void gameSeriesProvidesGameWhenOpen()
    {
        assertThat( openGame.getGame(), is(game) );
    }

    @Test
    public void gameSeriesHidesGameWhenClosed()
    {
        assertThat( closedGame.getGame(), is(nullValue()) );
    }

    @Test
    public void gameSeriesHidesGameWhenSolved()
    {
        openGame.makeSolved();
        assertThat( openGame.getGame(), is(nullValue()) );
    }

    @Test
    public void gameSeriesKnowsGameId()
    {
        when( game.getId() ).thenReturn( "game_id" );
        assertThat( closedGame.getId(), is("game_id") );
    }

    @Test
    public void closedGameSeriesCanBeOpen() {
        closedGame.makeOpen();
        assertThat( closedGame.getState(), is(GameSeriesState.OPEN) );
    }

    @Test
    public void closedGameSeriesCannotBeSolved() {
        closedGame.makeSolved();
        assertThat( closedGame.getState(), is(GameSeriesState.CLOSED) );
    }

    @Test
    public void openGameSeriesCanBeSolved() {
        openGame.makeSolved();
        assertThat( openGame.getState(), is(GameSeriesState.SOLVED) );
    }

    @Test
    public void solvedGameSeriesCannotBeOpenAgain() {
        openGame.makeSolved();
        openGame.makeOpen();
        assertThat( openGame.getState(), is(GameSeriesState.SOLVED) );
    }

    @Test
    public void gameSeriesInitiallyHasNoWonGames() {
        assertThat( openGame.wonGamesCount(), is(0) );
    }

    @Test
    public void wonGamesCounterIncrementsWhenGameHasBeenWon() {
        // допустим, мы выиграли игру
        assertThat( openGame.wonGamesCount(), is(0) );
    }

    @Test
    public void openGameSeriesCanProduceNewGame() {
        GameSeries series = openGame;
//        Game game = series.game();
//        game -> ?
    }
}
