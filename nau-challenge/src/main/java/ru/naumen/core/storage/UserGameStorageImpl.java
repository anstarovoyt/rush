package ru.naumen.core.storage;

import java.util.Collection;
import java.util.LinkedHashMap;

import ru.naumen.core.game.GameProvider;
import ru.naumen.core.game.GameSeries;

import com.google.common.collect.Maps;

/**
 *
 *
 * @author astarovoyt
 *
 */
public class UserGameStorageImpl implements UserGameStorage
{
    private static final long serialVersionUID = 1L;

    private LinkedHashMap<String, GameSeries> games = Maps.newLinkedHashMap();

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

}
