package nauhchallenge.proto;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * По идее, крестики-нолики переводятся как "Tic Tac Toe"
 * Но это, сцуко, долго писать. Поэтому будет XO
 *
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOTest
{
    @Test
    public void xoGameMustHaveDescription() {
        XOGame game = new XOGame();
        assertThat(game.getDescription(), containsString("board"));
    }
}
