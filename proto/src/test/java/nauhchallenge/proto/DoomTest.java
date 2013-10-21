package nauhchallenge.proto;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class DoomTest
{
    @Test
    public void allOk() {
        assertThat(true, is(true));
    }

    @Test
    public void doomGameMustHaveDescription() {
        Doom doom = new Doom();
        assertThat(doom.getDescription(), containsString( "monsters" ));
    }

    @Test
    public void doomGameCannotBeWonByAnyString() {
        Doom doom = new Doom();
        doom.input("please let me out!");
        assertThat( doom.gameWon(), is(false) );
    }

    @Test
    public void doomGameCanBeWonByGodCheat() {
        Doom doom = new Doom();
        doom.input("iddqd");
        assertThat( doom.gameWon(), is(true) );
    }
}
