package ru.naumen.core.game.impl;

import static com.google.common.base.Predicates.and;
import static com.google.common.collect.Iterators.filter;
import static com.google.common.collect.Range.greaterThan;
import static com.google.common.collect.Range.lessThan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 31.10.13
 */
public class Fifteen implements Game
{
    private static final Fifteen ORDERED_FIELD = new Fifteen(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0);

    private static final long serialVersionUID = 2988175912527130402L;

    final List<Integer> field;

    GameState state = GameState.IN_PROGRESS;
    private boolean impossibleMoveDetected;
    private boolean notIntegerValuesInUserInput;

    /**
     * 50% всех случайных расположений не сходятся к стандартному, поэтому перед созданием игры
     * надо проверять, что мы создали сходящуюся комбинацию.
     * http://ru.wikipedia.org/wiki/%D0%9F%D1%8F%D1%82%D0%BD%D0%B0%D1%88%D0%BA%D0%B8
     * @return
     */
    public static boolean isValid( List<Integer> list )
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : list) {
            map.put( item, list.indexOf( item ) );
        }
        int sum = 0;
        for (Integer item = 1; item <= 15; item++) {
            Iterator<Integer> following = list.subList( map.get(item) + 1, 16 ).iterator();
            ArrayList<Integer> filtered = Lists.newArrayList(
                    filter( following, and( lessThan( item ), greaterThan( 0 ) ) ) );
            sum += filtered.size();
        }
        sum += map.get( 0 ) + 1;
        return (sum % 2) == 0;
    }

    public Fifteen()
    {
        // а тут может быть подстава, не все комбинации приводимы к ORDERED_FIELD
        List<Integer> items = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 8, 7, 9, 10, 11, 12, 13, 14, 15);
        do {
            Collections.shuffle(items);
        } while( !isValid( items ) );
        field = items;
    }

    public Fifteen( Integer... initialPosition )
    {
        field = Arrays.asList( initialPosition );
    }

    @Override
    public String getStateRepresentation()
    {
        return "<pre>" + String.format( " %2s | %2s | %2s | %2s <br>", repr(0), repr(1), repr(2), repr(3) ) +
                String.format( " %2s | %2s | %2s | %2s <br>", repr(4), repr(5), repr(6), repr(7) ) +
                String.format( " %2s | %2s | %2s | %2s <br>", repr(8), repr(9), repr(10), repr(11) ) +
                String.format( " %2s | %2s | %2s | %2s <br>", repr(12), repr(13), repr(14), repr(15) ) + "</pre>";
    }

    @Override
    public String getDescription()
    {
        return "Поле 4х4 содержит фишки, пронумерованные от 1 до 15.<br>" +
               "Нужно указать последовательность движений, которая приводит к тому, что все фишки становятся упорядоченными.<br>" +
               "Каждый ход - это просто номер фишки, которая должна сдвинуться в сторону свободной ячейки.<br>" +
               "Вы должны перечислить все ходы за один раз. Просто разделите их пробелами." +
               "После всех ходов игра должна выглядеть так:<br>" + ORDERED_FIELD.getStateRepresentation();
    }

    @Override
    public String getId()
    {
        return "fftn";
    }

    @Override
    public String getShortDescription()
    {
        return "Упорядочи хаос";
    }

    @Override
    public String getShortName()
    {
        return "Пятнашки";
    }

    @Override
    public void input( String userInput )
    {
        resetErrorMessages();
        processInput(userInput);
        if (isSorted())
            state = GameState.VICTORY;
        else
            state = GameState.IN_PROGRESS;
    }

    private void resetErrorMessages()
    {
        impossibleMoveDetected = false;
        notIntegerValuesInUserInput = false;
    }

    private void processInput(String userInput)
    {
        try {
            applyMoves(toIntegers( userInput ));
        } catch( NumberFormatException e )
        {
            notIntegerValuesInUserInput = true;
        }
    }

    private void applyMoves(List<Integer> moves)
    {
        for (Integer cell: moves) {
            int currentPosition = field.indexOf( cell );
            int emptyCellPosition = field.indexOf(0);
            if (canMoveCurrentCell(currentPosition, emptyCellPosition)) {
                field.set( emptyCellPosition, cell );
                field.set( currentPosition, 0 );
            }
            else {
                // cannot find move!
                impossibleMoveDetected = true;
                break;
            }
        }
    }

    private boolean canMoveCurrentCell(int currentPosition, int emptyCellPosition)
    {
        return canMoveLeft(currentPosition, emptyCellPosition) ||
            canMoveRight(currentPosition, emptyCellPosition) ||
            canMoveUp(currentPosition, emptyCellPosition) ||
            canMoveDown(currentPosition, emptyCellPosition);
    }

    private boolean canMoveLeft(int currentPosition, int emptyCellPosition)
    {
        if ((currentPosition + 1) % 4 == 1)
            return false;
        return emptyCellPosition == currentPosition - 1;
    }

    private boolean canMoveRight(int currentPosition, int emptyCellPosition)
    {
        if ((currentPosition + 1) % 4 == 0)
            return false;
        return emptyCellPosition == currentPosition + 1;
    }

    private boolean canMoveUp(int currentPosition, int emptyCellPosition)
    {
        return emptyCellPosition == currentPosition - 4;
    }

    private boolean canMoveDown(int currentPosition, int emptyCellPosition)
    {
        return emptyCellPosition == currentPosition + 4;
    }

    private List<Integer> toIntegers( String s )
    {
        List<Integer> list = new ArrayList<>();
        if (s.isEmpty())
            return list;
        for (String x: s.split( " " ))
            list.add( Integer.valueOf( x ) );
        return list;
    }

    private boolean isSorted()
    {
        return ORDERED_FIELD.field.equals(field);
    }

    @Override
    public String output()
    {
        if (impossibleMoveDetected)
            return "Предложенный ход невозможен";
        if (notIntegerValuesInUserInput)
            return "Некорректный ввод";
        return null;
    }

    @Override
    public Game resetState() {
        return new Fifteen();
    }

    @Override
    public GameState state()
    {
        return state;
    }

    /**
     * String representation of one cell
     */
    private String repr(int index)
    {
        Integer value = field.get(index);
        if (value == 0)
        {
            return "";
        }
        return String.format( "%d", value );
    }
}
