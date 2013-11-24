package ru.naumen.core.rating;

/**
 * Row with rating info
 *
 * @author serce
 * @since 31 oct. 2013 г.
 */
public class RatingRow
{

    private String commandName;
    private int score;
    private long lastSolved;

    public RatingRow(String commandName, int score, long lastSolved)
    {
        this.commandName = commandName;
        this.score = score;
        this.lastSolved = lastSolved;
    }

    public String getCommandName()
    {
        return commandName;
    }

    public long getLastSolved()
    {
        return lastSolved;
    }

    public int getScore()
    {
        return score;
    }
}
