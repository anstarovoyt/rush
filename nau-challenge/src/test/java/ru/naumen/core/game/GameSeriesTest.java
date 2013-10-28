package ru.naumen.core.game;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
    GameSeries closedGame = GameSeries.closedGame( game, 1);
    GameSeries openGame = GameSeries.openGame( game, 1 );

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
    public void defaultMaxWinsCountIs_1() {
        assertThat( openGame.maxWinsCount(), is(1) );
    }

    @Test
    public void wonGamesCounterIncrementsWhenGameHasBeenWon() {
        openGame.winOneGame();
        assertThat( openGame.wonGamesCount(), is( 1 ) );
    }

    @Test
    public void gameSeriesBecameSolvedWhenAllGamesHasBeenWon() {
        openGame = GameSeries.openGame( game, 2 );
        openGame.winOneGame();
        assertThat( openGame.getState(), is(GameSeriesState.OPEN));
        openGame.winOneGame();
        assertThat( openGame.getState(), is(GameSeriesState.SOLVED));
    }

    @Test
    public void maxGamesCounterCanBeSetOnCreation() {
        openGame = GameSeries.openGame( game, 3 );
        assertThat( openGame.maxWinsCount(), is(3) );
    }

    @Test
    public void openGameSeriesCanProduceNewGame() {
        GameSeries closedGame1 = mock(GameSeries.class);
        GameSeries closedGame2 = mock(GameSeries.class);

        openGame = GameSeries.openGame( game, 1, closedGame1, closedGame2 );
        openGame.winOneGame();
        verify( closedGame1 ).makeOpen();
        verify( closedGame2 ).makeOpen();
    }

    @Test
    public void winsCounterResetsToZeroWhenOneGameHasBeenLosed() {
        openGame = GameSeries.openGame( game, 3 );
        openGame.winOneGame();
        openGame.winOneGame();
        openGame.loseOneGame();
        assertThat( openGame.wonGamesCount(), is(0) );
    }
}
