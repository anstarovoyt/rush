package ru.naumen.core.game.impl;

import org.junit.Assert;
import org.junit.Test;

import ru.naumen.core.game.GameState;

/**
 * Тесты для камень-ножницы-бумага
 *
 * @author astarovoyt
 *
 */
public class RPSGameTest
{
    public static final String ORDERING_WIN = "SPRSSPPRR";

    @Test
    public void testMovedOrdering()
    {
        RPSGame game = new RPSGame();
        game.setState(-1 + RPSGame.ORDERING.length());

        StringBuilder actual = new StringBuilder();
        for (int i = 0; i < RPSGame.ORDERING.length(); i++)
        {
            game.input("R");
            actual.append(game.currentComputerStep);
        }

        Assert.assertEquals(RPSGame.ORDERING, actual.toString());
    }

    @Test
    public void testOrdering()
    {
        RPSGame game = new RPSGame();
        game.setState(-1);

        StringBuilder actual = new StringBuilder();
        for (int i = 0; i < RPSGame.ORDERING.length(); i++)
        {
            game.input("R");
            actual.append(game.currentComputerStep);
        }

        Assert.assertEquals(RPSGame.ORDERING, actual.toString());
    }

    @Test
    public void testPaperDefeatRock()
    {
        RPSGame game = new RPSGame();

        Assert.assertTrue(game.isUserWin('P', 'R'));
    }

    @Test
    public void testRockDefeatScissors()
    {
        RPSGame game = new RPSGame();

        Assert.assertTrue(game.isUserWin('R', 'S'));
    }

    @Test
    public void testScissorsDefeatPaper()
    {
        RPSGame game = new RPSGame();

        Assert.assertTrue(game.isUserWin('S', 'P'));
    }

    @Test
    public void testScissorsNotDefeatRock()
    {
        RPSGame game = new RPSGame();

        Assert.assertFalse(game.isUserWin('S', 'R'));
    }

    @Test
    public void testSelfNotDefeatSelf()
    {
        RPSGame game = new RPSGame();

        Assert.assertFalse(game.isUserWin('S', 'S'));
        Assert.assertFalse(game.isUserWin('R', 'R'));
        Assert.assertFalse(game.isUserWin('P', 'P'));
    }

    @Test
    public void testUserCanLoseAfter19Times()
    {
        RPSGame game = new RPSGame();
        game.setState(-1);
        for (int i = 0; i < 19; i++)
        {
            game.input(String.valueOf(ORDERING_WIN.charAt(i % RPSGame.ORDERING.length())));
            Assert.assertEquals(GameState.VICTORY, game.state());
        }

        game.input(String.valueOf(RPSGame.ORDERING.charAt(19 % RPSGame.ORDERING.length())));
        Assert.assertEquals(GameState.FAILURE, game.state());

    }

    @Test
    public void testUserCanWon20Times()
    {
        RPSGame game = new RPSGame();
        game.setState(-1);
        for (int i = 0; i < 20; i++)
        {
            game.input(String.valueOf(ORDERING_WIN.charAt(i % RPSGame.ORDERING.length())));
            Assert.assertEquals(GameState.VICTORY, game.state());
        }

    }
}
