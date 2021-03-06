package ru.naumen.model.dao;

import com.google.common.collect.Iterables;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * DAO for user entity
 * <br>
 * Used JPA specification
 * @author serce, astarovoyt
 *
 */
@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserDAO
{
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

    @Transactional(readOnly = true)
    public List<User> loadAll()
    {
        return getEntityManager().createQuery("SELECT p from " + User.class.getName() + " p").getResultList();
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
