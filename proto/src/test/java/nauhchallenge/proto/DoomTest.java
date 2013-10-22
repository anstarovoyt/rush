package nauhchallenge.proto;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class DoomTest
{
    @Test
    public void doomGameMustHaveDescription() {
        Game doom = new Doom();
        assertThat(doom.getDescription(), containsString( "monsters" ));
    }

    @Test
    public void doomGameCannotBeWonOrLoseBeforeTheFirstInput() {
        Game doom = new Doom();
        assertThat( doom.state(), is(GameState.IN_PROGRESS) );
    }

    @Test
    public void doomGameCannotBeWonByAnyString() {
        Game doom = new Doom();
        doom.input("please let me out!");
        assertThat( doom.state(), is( GameState.FAILURE ) );
    }

    @Test
    public void doomGameKillsPlayerOnWrongInput() {
        Game doom = new Doom();
        doom.input( "test" );
        assertThat( doom.output(), is("You have been killed") );
    }

    @Test
    public void doomGameCanBeWonByGodCheat() {
        Game doom = new Doom();
        doom.input("iddqd");
        assertThat( doom.state(), is(GameState.VICTORY) );
    }

    @Test
    @Ignore ("надо обсудить и решить, зависят ли решения от регистра при вводе")
    public void doomGameMustIgnoreUserInputInWrongCapitalization_orNot() {
        Game doom = new Doom();
        doom.input("IDDQD");
        assertThat( doom.state(), is(GameState.FAILURE) );
    }

    @Test
    @Ignore ("потом будет видно, реентерабельны игры или нет")
    public void doomGameIsReenterable_orNot() {
        Game doom = new Doom();
        doom.input("iddqd");
        doom.input("wrong data");
        assertThat( doom.state(), is(GameState.FAILURE) );
    }
}
