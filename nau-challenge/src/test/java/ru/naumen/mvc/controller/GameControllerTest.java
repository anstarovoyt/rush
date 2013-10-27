package ru.naumen.mvc.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
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
//
//    @Before
//    public void setupMocks() {
//        MockitoAnnotations.initMocks( this );
//        mockMvc = standaloneSetup(gameController).build();
//        when(gameProvider.getGame( anyString(), anyString() )).thenReturn(game);
//    }
//
//    @Test
//    public void addGameDescriptionToModel() throws Exception
//    {
//        when( game.getDescription() ).thenReturn( "wat" );
//        mockMvc.perform( get( "/game" ) )
//                .andExpect( model().attribute( "description", "wat" ));
//    }
}
