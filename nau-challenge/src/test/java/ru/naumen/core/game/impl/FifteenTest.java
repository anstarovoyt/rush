package ru.naumen.core.game.impl;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 31.10.13
 */
public class FifteenTest
{
    @Test
    public void outputContainsAllNumbers() {
        Fifteen game = new Fifteen(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertThat(game.getStateRepresentation(), allOf(
                containsString( "    |  1 |  2 |  3 " ),
                containsString( "  4 |  5 |  6 |  7 " ),
                containsString( "  8 |  9 | 10 | 11 " ),
                containsString( " 12 | 13 | 14 | 15 " )));
    }

    @Test
    public void outputContainsAllNumbersForDefaultConstructor() {
        Fifteen game = new Fifteen();
        assertThat(game.getStateRepresentation(), allOf(
                containsString( "    "), containsString("  1 "), containsString("  2 "), containsString("  3 " ),
                containsString( "  4 "), containsString("  5 "), containsString("  6 "), containsString("  7 " ),
                containsString( "  8 "), containsString("  9 "), containsString(" 10 "), containsString(" 11 " ),
                containsString( " 12 "), containsString(" 13 "), containsString(" 14 "), containsString(" 15 " )));
    }

    @Test
    public void validCombination() {
        assertThat( Fifteen.isValid( Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 )), is(true) );
    }

    @Test
    public void invalidCombination() {
        assertThat( Fifteen.isValid(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 14, 0)), is(false) );
    }

    @Test
    public void allGamesAreValid() {
        for (int i = 0; i < 10; i++)
            assertThat( Fifteen.isValid( new Fifteen().field), is(true) );
    }

    @Test
    public void notIntegerMovesAreNotAllowed() {
        Fifteen game = new Fifteen();
        game.input("test");
        assertThat(game.state(), is( GameState.FAILURE ));
    }
}
