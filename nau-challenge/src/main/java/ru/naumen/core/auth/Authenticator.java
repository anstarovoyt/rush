package ru.naumen.core.auth;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

import com.google.common.base.Objects;

/**
 * Простейший аутентификатор, чтобы не заморачиваться со Spring Security
 * 
 * @author serce
 * @since 25 окт. 2013 г.
 */
@Component
public class Authenticator {
    
    @Inject
    UserDAO userDAO;
    
    private boolean checkPass(User dbUser, String password) {
        return Objects.equal(dbUser.getPassword(), password);
    }


    public boolean checkAuth(String email, String password) {
        User dbUser = userDAO.getByEmail(email);
        return checkPass(dbUser, password);
    }
    
}
