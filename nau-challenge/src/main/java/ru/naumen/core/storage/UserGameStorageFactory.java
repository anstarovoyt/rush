package ru.naumen.core.storage;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameProvider;

/**
 * @author astarovoyt
 *
 */
@Component
public class UserGameStorageFactory
{
    @Inject
    GameProvider provider;

    public UserGameStorage create()
    {
        UserGameStorageImpl impl = new UserGameStorageImpl();
        for (Game game : provider.getNewGameList())
        {
            impl.put(game.getId(), game);
        }

        return impl;
    }
}
