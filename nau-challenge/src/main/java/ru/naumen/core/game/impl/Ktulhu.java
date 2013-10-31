package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * User: anstarovoyt
 * Date: 11/1/13
 * Time: 1:46 AM
 */
public class Ktulhu implements Game {

    public static final String EXPECTED = "```sii``s`k.n``s`k.g``s`k.a``s`k.t``s`k.h``s`k.f``s`k. ``s`k.u``s`k.h``s`k.l``s`k.u``s`k.h``s`k.t``s`k.k`k.m";
    public static final String EXPECTED_2 = "```sii``s`k.n``s`k.g``s`k.a``s`k.t``s`k.h``s`k.f``s`k. ``s`k.u``s`k.h``s`k.l``s`k.u``s`k.h``s`k.t``s`k.c`k.m";

    GameState victory = GameState.IN_PROGRESS;


    @Override
    public String getShortName() {
        return "Демонология";
    }

    @Override
    public String getShortDescription() {
        return "Положите в котел мандрагору и заткните уши...";
    }

    @Override
    public String getStateRepresentation() {
        return "```sii``s`k.n``s`k.g``s`k.a``s`k.t``s`k.h``s`k.f``s`k. ``s`k.u``s`k.h``s`k.l``s`k.u``s`k.h``s`k.t`k.m";
    }

    @Override
    public String getDescription() {
        return "Колдун-программист Алексей решил призвать из недр Рльеха Ктулху. " +
                "Он изучил тонны литературы, посетил Баренцево море и окончательно убедился в бесполезности морских водорослей в салате." +
                " Он написал программу. " +
                "Но на призыв колдуна явился совершенно бесполезный Тулху, знаменитый лишь своими нетрадиционными предпочтениями. <br>" +
                "Помогите Алексею исправить положение и уничтожить мир.";
    }

    @Override
    public String getId() {
        return "tx";
    }

    @Override
    public void input(String userInput) {
        String processedInput = userInput.trim().toLowerCase();
        if (EXPECTED.equals(processedInput) || EXPECTED_2.equals(processedInput))
        {
            victory = GameState.VICTORY;
        }
        else
        {
            victory = GameState.FAILURE;
        }
    }

    @Override
    public String output() {
        return null;
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
