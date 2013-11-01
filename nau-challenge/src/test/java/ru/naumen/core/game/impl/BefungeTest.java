package ru.naumen.core.game.impl;

import org.junit.Assert;
import org.junit.Test;

import ru.naumen.core.game.GameState;

/**
 * @author serce
 * @since 02 нояб. 2013 г.
 */
public class BefungeTest
{
    @Test
    public void won() {
        Befunge doom = new Befunge();
        doom.input("11977551872");
        Assert.assertEquals(doom.state(), GameState.VICTORY);
    }

    @Test
    public void lose() {
        Befunge doom = new Befunge();
        doom.input("1");
        Assert.assertEquals(doom.state(), GameState.FAILURE);
    }
}
