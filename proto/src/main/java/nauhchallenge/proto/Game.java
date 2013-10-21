package nauhchallenge.proto;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public interface Game
{
    String getDescription();

    void input( String userInput );

    GameState state();

    String output();
}
