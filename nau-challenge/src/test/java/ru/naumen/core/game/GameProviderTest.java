package ru.naumen.core.game;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ru.naumen.core.game.impl.Doom;

/**
 * @author Andrey Hitrin
 * @since 27.10.13
 */
public class GameProviderTest
{
    GameProvider provider = new GameProvider();

    @Test
    public void nullReturnedForNotExistingKey()
    {
        Game game = provider.getGame( "not-existing-one", "" );
        assertThat(game, is(nullValue()));
    }

    @Test
    public void gameReturnedForExistingKey()
    {
        Game game = provider.getGame( "dm", "" );
        assertThat(game, instanceOf( Doom.class ));
    }
}
