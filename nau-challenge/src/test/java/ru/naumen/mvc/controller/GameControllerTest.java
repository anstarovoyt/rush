package ru.naumen.mvc.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import ru.naumen.core.auth.Authenticator;
import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameSeries;
import ru.naumen.core.game.GameSeriesState;
import ru.naumen.core.game.GameState;
import ru.naumen.core.storage.UserGameStorage;
import ru.naumen.model.User;

/**
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameControllerTest
{
    MockMvc mockMvc;

    @InjectMocks GameController gameController;

    @Mock Game game;
    @Mock Authenticator authenticator;
    @Mock User user;
    @Mock UserGameStorage storage;
    @Mock GameSeries gameSeries;

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks( this );
        mockMvc = standaloneSetup(gameController).build();
        when( authenticator.getCurrentUser() ).thenReturn( user );
        when( user.getUserGameStorage() ).thenReturn( storage );
        when( storage.get( anyString() ) ).thenReturn( gameSeries );
        when( gameSeries.getGame() ).thenReturn( game );
    }

    @Test
    public void addGameDescriptionToModel() throws Exception
    {
        when( game.getDescription() ).thenReturn( "wat" );
        mockMvc.perform( get( "/game" ) )
                .andExpect( model().attribute( "description", "wat" ) );
    }

    @Test
    public void addGameStatisticsToModel() throws Exception
    {
        when(gameSeries.maxWinsCount()).thenReturn( 10 );
        when( gameSeries.wonGamesCount() ).thenReturn( 3 );
        mockMvc.perform( get("/game") )
                .andExpect( model().attribute( "wins", 3 ) )
                .andExpect( model().attribute( "maxwins", 10 ) );
    }

    @Test
    public void doNotIncrementWonGamesCountWhenGameIsInProgress() throws Exception
    {
        when( game.state() ).thenReturn( GameState.IN_PROGRESS );
        mockMvc.perform( post("/game") );
        verify( gameSeries, never() ).winOneGame();
    }

    @Test
    public void incrementWonGamesCount() throws Exception
    {
        when( game.state() ).thenReturn( GameState.VICTORY );
        mockMvc.perform( post( "/game" ) );
        verify( gameSeries ).winOneGame();
    }

    @Test
    public void whenGameIsClosedThenItForwardsToClosedGamePage() throws Exception
    {
        when( gameSeries.getState() ).thenReturn( GameSeriesState.CLOSED );
        mockMvc.perform( get("/game") )
                .andExpect( forwardedUrl( "gameclosed" ) );
    }

    @Test
    public void whenGameHasBeenWonThenItForwardsToSolvedGamePage() throws Exception
    {
        when( gameSeries.getState() ).thenReturn( GameSeriesState.SOLVED );
        when(gameSeries.wonGamesCount()).thenReturn( 5 );

        mockMvc.perform( get("/game") )
                .andExpect( forwardedUrl( "gamesolved" ) )
                .andExpect( model().attribute( "wins", 5 ) );
    }

    @Test
    public void solveGameWhenWinsCountReachesLimit() throws Exception
    {
        when( game.state() ).thenReturn( GameState.VICTORY );
        when( gameSeries.wonGamesCount()).thenReturn( 1 );
        when( gameSeries.getState() ).thenReturn( GameSeriesState.SOLVED );

        mockMvc.perform( post("/game") )
                .andExpect( forwardedUrl( "gamesolved" ) )
                .andExpect( model().attribute( "wins", 1 ) );
    }

    @Test
    public void resetWinsCountWhenGameHasBeenLost() throws Exception
    {
        when( game.state() ).thenReturn( GameState.FAILURE );
        mockMvc.perform( post( "/game" ) );
        verify( gameSeries ).loseOneGame();
    }
}
