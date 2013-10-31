package ru.naumen.core.auth;

import static ru.naumen.core.info.Params.ACCESS_KEY_PARAM;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import ru.naumen.core.SpringContext;
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

import com.google.common.base.Objects;

/**
 * Простейший аутентификатор, чтобы не заморачиваться со Spring Security
 *
 * @author serce
 * @since 25 окт. 2013 г.
 */
@Component("authenticator")
public class Authenticator
{

    public static final Logger LOG = Logger.getLogger(Authenticator.class);

    @Inject
    UserDAO userDAO;

    public boolean authByAccessKey(String accessKey)
    {
        User user = userDAO.getByAccessKey(accessKey);
        if (user != null)
        {
            setCurrentUser(user);
        }
        else
        {
            LOG.debug("incorrent access key try " + accessKey);
        }
        return user != null;
    }

    public boolean checkAuth(String email, String password)
    {
        User dbUser = userDAO.getByEmail(email);
        return dbUser == null ? false : checkPass(dbUser, password);
    }

    public User getCurrentUser()
    {
        User user = null;
        String accessKey = (String)SpringContext.session().getAttribute(ACCESS_KEY_PARAM);
        if (accessKey != null)
        {
            user = userDAO.getByAccessKey(accessKey);
            if (user != null)
            {
                setCurrentUser(user);
            }
        }
        return user;
    }

    public void logout()
    {
        SpringContext.session().removeAttribute(ACCESS_KEY_PARAM);
    }

    public User registerUser(User user)
    {
        validateUser(user);
        user = userDAO.saveUser(user);
        user = setCurrentUser(user);
        return user;
    }

    public User setCurrentUser(String email)
    {
        User user = userDAO.getByEmail(email);
        SpringContext.session().setAttribute(ACCESS_KEY_PARAM, user.getAccessKey());
        return user;
    }

    public User setCurrentUser(User user)
    {
        SpringContext.session().setAttribute(ACCESS_KEY_PARAM, user.getAccessKey());
        return user;
    }

    private boolean checkPass(User dbUser, String password)
    {
        return Objects.equal(dbUser.getPassword(), password);
    }

    private void validateUser(User user)
    {
    }
}
