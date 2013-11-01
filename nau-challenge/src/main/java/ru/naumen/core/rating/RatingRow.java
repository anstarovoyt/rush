package ru.naumen.core.rating;


/**
 * @author serce
 * @since 31 окт. 2013 г.
 */
public class RatingRow {

    private String commandName;
    private int score;
    private long lastSolved;

    public RatingRow(String commandName, int score, long lastSolved) {
        this.commandName = commandName;
        this.score = score;
        this.lastSolved = lastSolved;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getScore() {
        return score;
    }

    public long getLastSolved() {
        return lastSolved;
    }
}
