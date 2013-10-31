package ru.naumen.model;

import java.util.Random;

import javax.persistence.*;

import ru.naumen.core.SpringContext;
import ru.naumen.core.auth.accessKey.AccessKeyGenerator;
import ru.naumen.core.storage.UserGameStorage;
import ru.naumen.core.storage.UserGameStorageFactory;

/**
 * Пользователь системы
 *
 * @author serce
 * @since 25 окт. 2013 г.
 */
@Entity
@Table(name = "users")
@Cacheable
public class User
{
    public static User create(String email, String fio, String password)
    {
        User user = new User();
        user.setId(new Random().nextInt());
        user.setEmail(email);
        user.setFio(fio);
        user.setPassword(password);
        user.setAccessKey(AccessKeyGenerator.generateNewAccessKey());
        user.setUserStorage(((UserGameStorageFactory)SpringContext.getBean("userGameStorageFactory")).create());
        return user;
    }

    @Id
    private long id;

    @Column
    private String email;

    @Column
    private String fio;

    @Column
    private String password;

    @Column
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private UserGameStorage storage;

    @Column
    private String accessKey;

    User()
    {
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (!(obj instanceof User))
        {
            return false;
        }
        return id == ((User)obj).getId();
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public String getEmail()
    {
        return email;
    }

    public String getFio()
    {
        return fio;
    }

    public long getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public UserGameStorage getUserGameStorage()
    {
        return storage;
    }

    @Override
    public int hashCode()
    {
        return (int)(id + id << 31);
    }

    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setFio(String fio)
    {
        this.fio = fio;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUserStorage(UserGameStorage storage)
    {
        this.storage = storage;
    }

    @Override
    public String toString()
    {
        return String.format("[user: id %i, command %s, email %s]", id, fio, email);
    }
}
