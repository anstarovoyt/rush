package ru.naumen.core.storage;

import java.io.Serializable;
import java.util.Collection;

import ru.naumen.core.game.GameSeries;

/**
 * "Песочница" игр для текущего пользователя
 * В ней должно храниться текущее состояние для каждой из игр
 * <p/>
 * Представляет из себя key-value с ключем в виде строки
 * Считаем, что в хранилище мы кладем только простые объекты,
 * пока не требуется большего
 * <p/>
 * Дабы не усложнять себе жизнь можем считать что хранилище
 * будет использоваться только одним потоком
 * <p/>
 * User: anstarovoyt
 * Date: 10/23/13
 * Time: 11:13 PM
 */
public interface UserGameStorage extends Serializable {

    GameSeries get(String key);

    Collection<GameSeries> getAll();
}
