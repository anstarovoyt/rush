package nauhchallenge.proto;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
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
        Game game = new XOGame();
        assertThat(game.getDescription(), containsString("board"));
    }

    @Test
    public void xoGamePutsOneOForOneX() {
        // это прототип, поэтому игра работает детерминированно
        // сейчас важно не игру сделать, а понять, какой нужен интерфейс
        Game game = new XOGame();
        game.input( "...|.X.|..." );
        assertThat( game.output(), is("O..|.X.|...") );
    }

    @Test
    public void xoGameCannotBeWonBySingleOutput() {
        // а вот тут становится понятно, что надо отделять понятие "игра завершена" от "игра не завершена"
        // игра ещё не выиграна после одного хода, но игрок должен иметь возможность делать дальнейшие ходы
        Game game = new XOGame();
        game.input( "...|.X.|..." );
        assertThat( game.state(), is(GameState.IN_PROGRESS) );
    }
}
