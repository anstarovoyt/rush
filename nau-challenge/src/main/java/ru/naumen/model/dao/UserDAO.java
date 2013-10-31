package ru.naumen.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.naumen.model.User;

import com.google.common.collect.Iterables;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserDAO
{
    public final static boolean IS_DEBUG = true;

    public static final String DB_NAME = "userdb";

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public User getByAccessKey(String accessKey)
    {
        Query query = getEntityManager().createQuery(
                "SELECT p from " + User.class.getName() + " p where p.accessKey = :accessKey");
        query.setParameter("accessKey", accessKey);
        query.setHint("org.hibernate.cacheable", true);

        List resultList = query.getResultList();
        return (User)Iterables.get(resultList, 0, null);
    }

    @Transactional
    public User getByEmail(String email)
    {
        Query query = getEntityManager().createQuery(
                "SELECT p from " + User.class.getName() + " p where p.email = :email");
        query.setParameter("email", email);
        query.setHint("org.hibernate.cacheable", true);

        List resultList = query.getResultList();
        return (User)Iterables.get(resultList, 0, null);
    }

    @Transactional
    public User saveUser(User user)
    {
        getEntityManager().merge(user);

        return user;
    }

    public void store(User user)
    {
        getEntityManager().persist(user);
    }

    EntityManager getEntityManager()
    {
        return entityManager;
    }
}
