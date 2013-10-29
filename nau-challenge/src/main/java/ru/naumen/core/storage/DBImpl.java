package ru.naumen.core.storage;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.naumen.model.User;

import com.google.common.collect.Iterables;

/**
 * @author astarovoyt
 *
 */
@Repository
public class DBImpl
{
    public final static boolean IS_DEBUG = true;

    public static final String DB_NAME = "userdb";


    @PersistenceContext
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    @Transactional
    public User findByAccessKey(String accessKey)
    {
        Query query = getEntityManager().createQuery("SELECT p from " + User.class.getName() + " p where p.accessKey = :accessKey");
        query.setParameter("accessKey", accessKey);
        query.setHint("org.hibernate.cacheable", true);

        List resultList = query.getResultList();
        return Iterables.get(resultList, 0, null);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public User findByEmail(String email)
    {
        Query query = getEntityManager().createQuery("SELECT p from " + User.class.getName() + " p where p.email = :email");
        query.setParameter("email", email);
        query.setHint("org.hibernate.cacheable", true);

        List resultList = query.getResultList();
        return Iterables.get(resultList, 0, null);
    }

    @PostConstruct
    public void init()
    {
    }

    @Transactional
    public void store(User user)
    {
        getEntityManager().persist(user);
    }

    EntityManager getEntityManager()
    {
        return entityManager;
    }
}
