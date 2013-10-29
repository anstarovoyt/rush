package ru.naumen.model.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import ru.naumen.core.storage.DBImpl;
import ru.naumen.model.User;

@Component
public class UserDAO
{
    @Inject
    DBImpl dbImpl;

    public User getByAccessKey(String accessKey)
    {
        return dbImpl.findByAccessKey(accessKey);
    }

    public User getByEmail(String email)
    {
        return dbImpl.findByEmail(email);
    }

    public User saveUser(User user)
    {
        dbImpl.store(user);

        return user;
    }
}
