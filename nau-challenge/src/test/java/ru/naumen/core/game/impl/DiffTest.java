package ru.naumen.core.game.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.naumen.core.game.GameState;

/**
 * @author astarovoyt
 *
 */
public class DiffTest
{
    @Test
    public void testCanLose()
    {
        Diff game  = new Diff();

        game.input("(150, 81) (100, 20) (160, 10)");

        assertEquals(GameState.FAILURE, game.state());
    }

    @Test
    public void testCanWin()
    {
        Diff game  = new Diff();

        game.input("(150, 80) (100, 20) (160, 10)");

        assertEquals(GameState.VICTORY, game.state());
    }
}
