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
    public void testFormatter()
    {
        String result = XOGame.formatter("   XXX000");

        assertEquals(" | | \nX|X|X\n0|0|0", result);
        System.out.print(result);
    }

//    @Test
//    public void playerCanDoStep()
//    {
//        XOGame game = new XOGame();
//
//        game.input("1");
//    }


}
