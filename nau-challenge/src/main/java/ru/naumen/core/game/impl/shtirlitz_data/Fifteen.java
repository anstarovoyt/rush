package ru.naumen.core.game.impl.shtirlitz_data;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 31.10.13
 */
public class Fifteen implements Game
{
    private final Integer[] position;

    public Fifteen()
    {
        // TODO: randomize
        this(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    }

    public Fifteen( Integer... initialPosition )
    {
        position = initialPosition;
    }

    @Override
    public String getShortName()
    {
        return "Пятнашки";
    }

    @Override
    public String getShortDescription()
    {
        return "Упорядочи хаос";
    }

    @Override
    public String computerOutput()
    {
        return "<pre>" + String.format( " %2s | %2s | %2s | %2s <br>", pos( 0 ), pos( 1 ), pos( 2 ), pos( 3 ) ) +
                String.format( " %2s | %2s | %2s | %2s <br>", pos( 4 ), pos( 5 ), pos( 6 ), pos( 7 ) ) +
                String.format( " %2s | %2s | %2s | %2s <br>", pos( 8 ), pos( 9 ), pos( 10 ), pos( 11 ) ) +
                String.format( " %2s | %2s | %2s | %2s <br>", pos( 12 ), pos( 13 ), pos( 14 ), pos( 15 ) ) + "</pre>";
    }

    private String pos( int index )
    {
        Integer value = position[ index ];
        if (value == 0)
            return "";
        return String.format( "%d", value );
    }

    @Override
    public String getDescription()
    {
        return "Поле 4х4 содержит фишки, пронумерованные от 1 до 15.<br>" +
               "Нужно указать последовательность движений, которая приводит к тому, что все фишки становятся упорядоченными.<br>" +
               "Каждый ход - это просто номер фишки, которая должна сдвинуться в сторону свободной ячейки.<br>" +
               "Вы должны перечислить все ходы за один раз. Просто разделите их пробелами.";
    }

    @Override
    public String getId()
    {
        return "fftn";
    }

    @Override
    public void input( String userInput )
    {
    }

    @Override
    public String output()
    {
        return computerOutput();
    }

    @Override
    public GameState state()
    {
        return null;
    }
}
