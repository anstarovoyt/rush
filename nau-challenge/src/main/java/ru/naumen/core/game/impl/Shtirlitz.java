package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 *
 * @author achernin
 * @since 30.10.13
 */
public class Shtirlitz implements Game
{

    public static final String ID = "stz";

    private static final long serialVersionUID = 1L;

    private static transient final String DESCRIPTION =
            "Штирлиц как никогда близок к провалу!<br/>" +
                            "Враги перехватили его зашифрованное сообщение.<br/>" +
                            "Враги знают, что в сообщении присутствует фраза 'Юстас Алексу'<br/>" +
                            "И врагов насторожило, что Штирлиц держит в книжном шкафу <a href=\"http://goo.gl/r9LsOs\">издание Анны Карениной на русском языке.</a></br>" +
                            "Наверное, для шифрования он использовал одну из глав этой книги. А пары чисел это номер строки и позиция символа в строке.<br/><br/>" +
                            "Расшифруйте перехваченное сообщение.";

    private static transient final String MESSAGE = "[(1, 1), (1, 41), (1, 41), (1, 65), (2, 508), (1, 3), (1, 6), (1, 14), (1, 3), (1, 7), (29, 370), (1, 25), (1, 2), (1, 5), (1, 25), (1, 7), (1, 8), (1, 50), (1, 3), (1, 10), (1, 25), (1, 66), (1, 7), (23, 242), (1, 24), (1, 10), (1, 5), (1, 7), (1, 4), (1, 9), (1, 41), (1, 24), (1, 5), (1, 50), (1, 3), (1, 6), (1, 5), (1, 7), (1, 72), (1, 110), (1, 25), (1, 110), (1, 50), (1, 5), (1, 194), (1, 2), (1, 3), (1, 7), (1, 12), (1, 41), (1, 10), (1, 2), (1, 41), (1, 9), (1, 5)]";

    private static transient final String MSG = "Сообщение Юстас Алексу Явка провалена Высылайте доктора";

    private GameState state = GameState.IN_PROGRESS;

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getShortDescription()
    {
        return "Помоги знаменитому разведчику!";
    }

    @Override
    public String getShortName()
    {
        return "Штирлиц";
    }

    @Override
    public String getStateRepresentation()
    {
        return MESSAGE;
    }

    @Override
    public void input(String userInput)
    {
        if (MSG.equals(userInput))
        {
            state = GameState.VICTORY;
        } else {
            state = GameState.FAILURE;
        }
    }

    @Override
    public String output()
    {
        return null;
    }

    @Override
    public Game resetState() {
        return this;
    }

    @Override
    public GameState state()
    {
        return state;
    }
}
