package ru.naumen.core.storage;

import ru.naumen.core.game.Game;

import java.util.Collection;

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
public interface UserGameStorage {

    Game get(String key);

    void put(String key, Game value);

    Collection<Game> getAll();
}
