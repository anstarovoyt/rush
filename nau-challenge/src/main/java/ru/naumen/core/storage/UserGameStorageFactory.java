package ru.naumen.core.storage;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

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
        return new UserGameStorageImpl(provider);
    }

}
