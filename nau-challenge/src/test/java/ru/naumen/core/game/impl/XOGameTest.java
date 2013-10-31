package ru.naumen.core.game.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.naumen.core.game.GameState;

/**
 * По идее, крестики-нолики переводятся как "Tic Tac Toe"
 * Но это, сцуко, долго писать. Поэтому будет XO
 *
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOGameTest
{

    /**
     * 0X0
     * X00
     * X0X
     *
     * User win (dead heat)
     */
    @Test
    public void isDeadHeatVictory()
    {
        XOGame game = new XOGame();

        game.setMatrix("O*OXOOX*X".toCharArray());

        game.input("2");

        assertArrayEquals("OXOXOOXOX".toCharArray(), game.matrix);

        assertEquals(GameState.VICTORY, game.state());
    }

    /**
     * 0X0
     * X00
     * X0X
     *
     * User win (dead heat)
     */
    @Test
    public void isDeadHeatVictory2()
    {
        XOGame game = new XOGame();

        game.setMatrix("O*OX*OXOX".toCharArray());

        game.input("2");

        assertArrayEquals("OXOXOOXOX".toCharArray(), game.matrix);

        assertEquals(GameState.VICTORY, game.state());
    }

    @Test
    public void playerCanDoInput()
    {
        XOGame game = new XOGame();

        game.input("1");

        String result = XOGame.unformat(game.getStateRepresentation());

        assertEquals(result.charAt(0), XOGame.USER_CHAR);
    }

    @Test
    public void playerCanLose()
    {
        XOGame game = new XOGame();

        game.setMatrix("X*****O*O".toCharArray());

        game.input("2");

        assertEquals(GameState.FAILURE, game.state());
    }

    @Test
    public void playerCannotDoIncorrectInput()
    {
        XOGame game = new XOGame();

        game.input("0");

        String result = XOGame.unformat(game.getStateRepresentation());

        assertEquals(result, "*********");
    }

    @Test
    public void playerCannotDoIncorrectInput2()
    {
        XOGame game = new XOGame();

        game.input("10");

        String result = XOGame.unformat(game.getStateRepresentation());

        assertEquals(result, "*********");
    }

    @Test
    public void playerCannotDoIncorrectInput3()
    {
        XOGame game = new XOGame();

        game.input("*");

        String result = XOGame.unformat(game.getStateRepresentation());

        assertEquals(result, "*********");
    }

    @Test
    public void playerCanNotSimpleWin()
    {
        XOGame game = new XOGame();

        game.setMatrix("X********".toCharArray());

        game.input("2");

        assertEquals(GameState.IN_PROGRESS, game.state());
        assertArrayEquals("XXO******".toCharArray(), game.matrix);
    }

    /**
     * XXX
     * ***
     * **O
     * User win
     */
    @Test
    public void playerCanWon()
    {
        XOGame game = new XOGame();

        game.setMatrix("X*X*****O".toCharArray());

        game.input("2");

        assertEquals(GameState.VICTORY, game.state());
    }

    @Test
    public void testFormat()
    {
        String result = XOGame.format("***XXXOOO".toCharArray());

        assertEquals("* | * | *<br>X | X | X<br>O | O | O", result);
    }


    @Test
    public void testUnformat()
    {
        String startInput = "***XXXOOO";
        String result = XOGame.unformat(XOGame.format(startInput.toCharArray()));

        assertEquals(startInput, result);
    }

    @Test
    public void winMorePriorityThanNotWinUser()
    {
        XOGame game = new XOGame();

        game.setMatrix("X*****OO*".toCharArray());

        game.input("2");

        assertEquals(GameState.FAILURE, game.state());
        assertArrayEquals("XX****OOO".toCharArray(), game.matrix);
    }
}
