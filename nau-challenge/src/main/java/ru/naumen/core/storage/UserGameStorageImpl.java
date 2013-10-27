package ru.naumen.core.storage;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.HashMap;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameProvider;

/**
 *
 *
 * @author astarovoyt
 *
 */
public class UserGameStorageImpl implements UserGameStorage
{

    private HashMap<String, Game> games = Maps.newHashMap();

    public UserGameStorageImpl( GameProvider provider )
    {
        for (Game game : provider.getNewGameList())
        {
            games.put( game.getId(), game );
        }
    }

    @Override
    public Game get(String key)
    {
        return games.get( key );
    }

    @Override
    public Collection<Game> getAll()
    {
        return games.values();
    }

    @Override
    public void put(String key, Game value)
    {
        games.put(key, value);
    }

}
