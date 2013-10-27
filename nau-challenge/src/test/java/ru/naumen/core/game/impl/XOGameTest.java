package ru.naumen.core.game.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * По идее, крестики-нолики переводятся как "Tic Tac Toe"
 * Но это, сцуко, долго писать. Поэтому будет XO
 *
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOGameTest
{

    @Test
    public void playerCanDoStep()
    {
        XOGame game = new XOGame();

        game.input("1");

    }

    @Test
    public void testFormat()
    {
        String result = XOGame.format("***XXXOOO".toCharArray());

        assertEquals("*|*|*\nX|X|X\nO|O|O", result);
    }

    @Test
    public void testUnformat()
    {
        String startInput = "***XXXOOO";
        String result = XOGame.unformat(XOGame.format(startInput.toCharArray()));

        assertEquals(startInput, result);
    }
}
