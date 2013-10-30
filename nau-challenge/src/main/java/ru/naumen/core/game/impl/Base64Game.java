package ru.naumen.core.game.impl;

import org.apache.commons.codec.binary.Base64;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * Таблица простых чисел здесь:
 * http://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%BF%D1%80%D0%BE%D1%81%D1%82%D1%8B%D1%85_%D1%87%D0%B8%D1%81%D0%B5%D0%BB
 *
 * @author Andrey Hitrin
 * @since 31.10.13
 */
public class Base64Game implements Game
{
    public static final String DESCRIPTION = "Таукитяне приветствуют братьев по разуму! " +
            "Чтобы подтвердить, что вы достаточно развиты, чтобы нам было, о чём поболтать, " +
            "пришлите в ответном сообщении 427-е простое число. Ждём с нетерпением!";
    private static final String ENCODED_DESCRIPTION = new String(Base64.encodeBase64( DESCRIPTION.getBytes() ));
    private GameState state = GameState.IN_PROGRESS;

    @Override
    public String getShortName()
    {
        return "Инопланетное послание";
    }

    @Override
    public String getShortDescription()
    {
        return "Из глубин космоса пришло что-то непонятное";
    }

    @Override
    public String computerOutput()
    {
        return null;
    }

    @Override
    public String getDescription()
    {
        return "Наши радиотелескопы уловили таинственное послание из космоса:<br>" + ENCODED_DESCRIPTION;
    }

    @Override
    public String getId()
    {
        return "quest";
    }

    @Override
    public void input( String userInput )
    {
        // надеюсь, я правильно определил, что 427-е простое число - это действительно 2963 :)
        state = "2963".equals( userInput ) ? GameState.VICTORY : GameState.FAILURE;
    }

    @Override
    public String output()
    {
        return null;
    }

    @Override
    public GameState state()
    {
        return state;
    }
}
