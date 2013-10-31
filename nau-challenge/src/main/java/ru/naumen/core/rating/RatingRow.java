package ru.naumen.core.rating;

import java.util.Date;

/**
 * @author serce
 * @since 31 окт. 2013 г.
 */
public class RatingRow {

    private String commandName;
    private int score;
    private Date lastSolved;

    public RatingRow(String commandName, int score, Date lastSolved) {
        this.commandName = commandName;
        this.score = score;
        this.lastSolved = lastSolved;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getLastSolved() {
        return lastSolved;
    }

    public void setLastSolved(Date lastSolved) {
        this.lastSolved = lastSolved;
    }
}
