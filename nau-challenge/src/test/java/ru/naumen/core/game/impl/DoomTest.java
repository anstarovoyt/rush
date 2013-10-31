package ru.naumen.core.game.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class DoomTest
{
    @Test
    public void doomGameCanBeWonByGodCheat() {
        Game doom = new Doom();
        doom.input("iddqd");
        assertThat( doom.state(), is(GameState.VICTORY) );
    }

    @Test
    public void doomGameCannotBeWonByAnyString() {
        Game doom = new Doom();
        doom.input("please let me out!");
        assertThat( doom.state(), is( GameState.FAILURE ) );
    }

    @Test
    public void doomGameCannotBeWonOrLoseBeforeTheFirstInput() {
        Game doom = new Doom();
        assertThat( doom.state(), is(GameState.IN_PROGRESS) );
    }

    @Test
    public void doomGameIsReenterable_orNot() {
        Game doom = new Doom();
        // сначала мы выигрываем
        doom.input("iddqd");
        // а что с игрой происходит потом?
        doom.input("wrong data");
        assertThat( doom.state(), is(GameState.FAILURE) );
    }

    @Test
    public void doomGameMustHaveDescription() {
        Game doom = new Doom();
        assertFalse(doom.getDescription().isEmpty());
    }
}
