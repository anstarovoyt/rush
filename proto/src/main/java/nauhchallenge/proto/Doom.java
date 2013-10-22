package nauhchallenge.proto;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class Doom implements Game
{
    GameState victory = GameState.IN_PROGRESS;

    @Override
    public String getDescription()
    {
        return "You have to fight evil martian monsters and find your way out from this awful maze!";
    }

    @Override
    public void input( String userInput )
    {
        victory = "iddqd".equals( userInput ) ? GameState.VICTORY : GameState.FAILURE;
    }

    @Override
    public GameState state()
    {
        return victory;
    }

    @Override
    public String output()
    {
        return "You have been killed";
    }
}
