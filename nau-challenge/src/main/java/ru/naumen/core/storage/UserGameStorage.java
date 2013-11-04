package ru.naumen.core.storage;

import java.io.Serializable;
import java.util.Collection;

import ru.naumen.core.game.GameSeries;

/**
 * @author astarovoyt
 */
public interface UserGameStorage extends Serializable
{

    GameSeries get(String key);

    Collection<GameSeries> getAll();

    void put(String id, GameSeries game);
}
