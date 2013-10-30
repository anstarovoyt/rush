package ru.naumen.core.game.impl;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ru.naumen.core.game.impl.shtirlitz_data.Fifteen;

/**
 * @author Andrey Hitrin
 * @since 31.10.13
 */
public class FifteenTest
{
    @Test
    public void outputContainsAllNumbers() {
        Fifteen game = new Fifteen(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertThat(game.output(), allOf(
                containsString( "    |  1 |  2 |  3 " ),
                containsString( "  4 |  5 |  6 |  7 " ),
                containsString( "  8 |  9 | 10 | 11 " ),
                containsString( " 12 | 13 | 14 | 15 " )));
    }

    @Test
    public void outputContainsAllNumbersForDefaultConstructor() {
        Fifteen game = new Fifteen();
        assertThat(game.output(), allOf(
                containsString( "    "), containsString("  1 "), containsString("  2 "), containsString("  3 " ),
                containsString( "  4 "), containsString("  5 "), containsString("  6 "), containsString("  7 " ),
                containsString( "  8 "), containsString("  9 "), containsString(" 10 "), containsString(" 11 " ),
                containsString( " 12 "), containsString(" 13 "), containsString(" 14 "), containsString(" 15 " )));
    }
}
