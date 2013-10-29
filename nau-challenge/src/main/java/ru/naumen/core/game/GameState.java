package ru.naumen.core.game;

import java.io.Serializable;

/**
 * <p>Пока что я не придумал игр, у которых было бы больше трёх состояний.
 * Надеюсь, их и не появится :)</p>
 *
 * <p>Если состояния у всех игр одинаковые, то можно обойтись простым enum-ом.
 * И будет нам щастье!</p>
 *
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public enum GameState implements Serializable
{
    IN_PROGRESS,
    FAILURE,
    VICTORY
}
