package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * User: anstarovoyt
 * Date: 11/2/13
 * Time: 3:06 AM
 */
public class Console implements Game {

    private static final long serialVersionUID = 1L;

    public static final String RMRF = "rm -rf";
    public static final String SUDO_RMRF = "sudo rm -rf";
    public static final String USER_INPUT = "user@pc:~$";
    public static final String PSW = "111";
    public static final String ID = "csl";
    GameState victory = GameState.IN_PROGRESS;
    boolean isSudo;
    String output;
    String representation = USER_INPUT;

    @Override
    public String getShortName() {
        return "Выстрел в ногу";
    }

    @Override
    public String getShortDescription() {
        return "Помоги пользователю выстрелить себе в ногу!";
    }

    @Override
    public String getStateRepresentation() {
        return representation;
    }

    @Override
    public String getDescription() {
        return "Помоги пользователю выстрелить себе в ногу!";
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void input(String userInput) {

        if (isSudo) {
            //Проверяем пароль
            if (PSW.equals(userInput.trim()))
            {
                victory = GameState.VICTORY;
            } else
            {
                output = "Incorrect password. Are you try brute force?";
                victory = GameState.IN_PROGRESS;
            }
            return;
        }

        if (userInput.startsWith(RMRF)) {
            output = "Permission denied. Are you root?";
            representation = USER_INPUT;
            victory = GameState.IN_PROGRESS;
            return;
        }

        if (userInput.startsWith(SUDO_RMRF)) {
            representation = "[sudo] password for user:";
            output = null;
            isSudo = true;
            victory = GameState.IN_PROGRESS;
            return;
        }

        victory = GameState.FAILURE;
    }

    @Override
    public String output() {
        return output;
    }

    @Override
    public GameState state() {
        return victory;
    }

    @Override
    public Game resetState() {
        return this;
    }
}
