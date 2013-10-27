package ru.naumen.mvc.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .andExpect( model().attribute( "description", "wat" ));
    }

    @Test
    public void whenGameIsClosedThenItRedirectsToClosedGamePage() throws Exception
    {
        when( gameSeries.getState() ).thenReturn( GameSeriesState.CLOSED );
        mockMvc.perform( get("/game") )
                .andExpect( forwardedUrl( "gameclosed" ) );
    }
}
