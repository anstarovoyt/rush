package nauhchallenge.proto;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class Doom
{
    boolean victory = false;

    public String getDescription()
    {
        return "You have to fight evil martian monsters and find your way out from this awful maze!";
    }

    public void input( String userInput )
    {
        victory = "iddqd".equals( userInput );
    }

    public boolean gameWon()
    {
        return victory;
    }
}
