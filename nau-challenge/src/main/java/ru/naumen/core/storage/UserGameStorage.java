package ru.naumen.core.storage;

import java.util.Collection;

import ru.naumen.core.game.Game;

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

    Collection<Game> getAll();

    /**
     * Метод кажется ненужным, но я его не удаляю. Вдруг, у кого-то на него завязана какая-то логика
     */
    @Deprecated
    void put(String key, Game value);
}
