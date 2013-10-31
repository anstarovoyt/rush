package ru.naumen.core.game.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import ru.naumen.core.game.GameState;

/**
 *
 * @author Andrey Hitrin
 * @since 01.11.13
 */
@RunWith(Theories.class)
public class FifteenMovesTest
{
    public static class G1
    {
        Fifteen game;
        String input;
        public G1(Fifteen game, String input) {
            this.game = game;
            this.input = input;
        }
    }

    public static class G2
    {
        Fifteen game;
        String input;
        public G2(Fifteen game, String input) {
            this.game = game;
            this.input = input;
        }
    }

    @DataPoints
    public static G1[] winCombinations = new G1[]{
        new G1(new Fifteen(1,  2,  3,  4,
                           5,  6,  7,  8,
                           9,  10, 11, 12,
                           13, 14, 15, 0), "" ),
        new G1( new Fifteen(1,  2,  3,  4,
                            5,  6,  7,  8,
                            9,  10, 11, 12,
                            13, 14, 0,  15), "15" ),
        new G1( new Fifteen(1,  2,  3,  4,
                            5,  6,  7,  8,
                            9,  10, 11, 12,
                            13, 0,  14, 15), "14 15" ),
        new G1( new Fifteen(1,  2,  3,  4,
                            5,  6,  7,  8,
                            9,  10, 0,  12,
                            13, 14, 11, 15), "11 15" ),
        new G1( new Fifteen(1,  2,  3,  4,
                            5,  6,  7,  8,
                            9,  10, 12, 0,
                            13, 14, 11, 15), "12 11 15" ),
        new G1( new Fifteen(1,  2,  3,  4,
                            5,  6,  7,  8,
                            9,  14, 10,  12,
                            13, 0, 11, 15), "14 10 11 15" ),
    };

    @DataPoints
    public static G2[] failCombinations = new G2[]{
        new G2(new Fifteen(1,  2,  3,  4,
                           5,  6,  7,  8,
                           9,  10, 11, 0,
                           12, 13, 14, 15), "12 13 14 15" ),
        new G2(new Fifteen(1,  2,  3,  4,
                           5,  6,  7,  8,
                           9,  10, 11, 13,
                           0, 14, 15, 12), "13 12" ),
        new G2(new Fifteen(1,  2,  3,  4,
                           5,  6,  7,  8,
                           9,  10, 11, 12,
                           13, 14, 0, 15), "11 11 11 15" ),
    };

    @Theory
    public void movesThatCausesVictory(G1 move) {
        move.game.input(move.input);
        assertThat(move.game.state(), is(GameState.VICTORY));
    }

    @Theory
    public void movesThatFail(G2 move) {
        move.game.input(move.input);
        assertThat(move.game.state(), is(GameState.FAILURE));
    }
}
