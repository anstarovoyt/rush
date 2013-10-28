package ru.naumen.core.storage;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.HashMap;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameProvider;
import ru.naumen.core.game.GameSeries;

/**
 *
 *
 * @author astarovoyt
 *
 */
public class UserGameStorageImpl implements UserGameStorage
{

    private HashMap<String, GameSeries> games = Maps.newHashMap();

    public UserGameStorageImpl( GameProvider provider )
    {
        for (GameSeries game : provider.getNewGameList())
        {
            games.put( game.getId(), game );
        }
    }

    @Override
    public GameSeries get(String key)
    {
        return games.get( key );
    }

    @Override
    public Collection<GameSeries> getAll()
    {
        return games.values();
    }

    @Override
    public void put(String key, Game value)
    {
    }

}
