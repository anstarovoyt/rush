package nauhchallenge.proto;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOGame implements Game
{
    @Override
    public String getDescription()
    {
        return "You have a 3x3 board. You can put X symbols anywhere, one per turn. Your task is to put 3 X'es in a line.";
    }

    @Override
    public void input( String userInput )
    {
    }

    @Override
    public boolean gameWon()
    {
        return false;
    }

    @Override
    public String output()
    {
        return "O..|.X.|...";
    }
}
