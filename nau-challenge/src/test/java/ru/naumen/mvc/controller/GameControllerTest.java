package ru.naumen.mvc.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameProvider;

/**
 * @author Andrey Hitrin
 * @since 25.10.13
 */
public class GameControllerTest
{
    MockMvc mockMvc;

    @InjectMocks GameController gameController;

    @Mock GameProvider gameProvider;
    @Mock Game game;

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks( this );
        mockMvc = standaloneSetup(gameController).build();
        when(gameProvider.getGame( anyString(), anyString() )).thenReturn(game);
    }

    @Test
    public void addGameDescriptionToModel() throws Exception
    {
        when( game.getDescription() ).thenReturn( "wat" );
        mockMvc.perform( get( "/game" ) )
                .andExpect( model().attribute( "description", "wat" ));
    }
}
