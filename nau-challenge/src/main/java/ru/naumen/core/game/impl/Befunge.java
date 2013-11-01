package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * &v+.@.<<<<                                                                      
 * #v--------                                                                      
 * #v[HELLO]-                                                                      
 * #v[DEVEL]#                                                                      
 * #v[RUSH-]*                                                                      
 * #5[CAMP-]2                                                                      
 * #+--------                                                                      
 * #>1-4+2v1\                                                                      
 * #v<<<<<<1^                                                                      
 * #>>>>>*>2^
 * 
 * http://progopedia.ru/language/befunge/
 * 
 * @author serce
 * @since 02 нояб. 2013 г.
 */
public class Befunge implements Game {
    
    public static final String ID = "zg";
    private static final long serialVersionUID = 1L;
    private static final String EXPECTED = "11977551872";
    
    GameState state = GameState.IN_PROGRESS;

    @Override
    public String getShortName() {
        return "Похмелье Васи";
    }

    @Override
    public String getShortDescription() {
        return "Помогите Васи пароли от бэкапа";
    }

    @Override
    public String getStateRepresentation() {
        return "<div style=\"font-family: monospace;\">&v+.@.<<<<<br/>"
                + "#v--------<br/>"
                + "#v[HELLO]-<br/>"
                + "#v[DEVEL]#<br/>"
                + "#v[RUSH-]*<br/>"
                + "#5[CAMP-]2<br/>"
                + "#+--------<br/>"
                + "#>1-4+2v1\\<br/>"
                + "#v<<<<<<1^<br/>"
                + "#>>>>>*>2^<br/></div>";
    }

    @Override
    public String getDescription() {
        return "После 24.00 Вася решил сделать бэкапы.<br/>"
                + "Конечно же их нельзя хранить без пароля, и Вася решил написать программу, чтобы придумать пароль!<br/>"
                + "Написав программу на своем любимом языке, он начал делать пароль:<br/>"
                + "- Ввел в программу 4, записал ответ<br/>"
                + "- Ввел в программу 6, записал ответ<br/>"
                + "- Придумал число, для которого программа на выходе выдает 40.<br/>"
                + "В итоге получил числа : a,b и с.  Паролем стало число (a*b)^c <br/>"
                + "Запаролив бэкапы Вася решил это отметить и увлекся, а проснувшись на следующий день понял, что от паролей "
                + "осталась только программа:<br/><br/>"

                + "Помогите Васе восстановить пароль. ";
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void input(String userInput) {
        state = EXPECTED.equals(userInput.trim()) ? GameState.VICTORY : GameState.FAILURE;
    }

    @Override
    public String output() {
        return null;
    }

    @Override
    public GameState state() {
        return state;
    }

    @Override
    public Game resetState() {
        return this;
    }

}
