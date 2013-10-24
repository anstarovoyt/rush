package ru.naumen.core.auth;

import javax.inject.Inject;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

/**
 * @author serce
 * @since 25 окт. 2013 г.
 */
@Component
public class UserService {

    @Inject
    UserDAO userDAO;
    @Inject
    ChallengeAuthProvider authProvider;
    
    private final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public User registerUser(User user) {
        validateUser(user);
        user = userDAO.saveUser(user);
        setCurrentUser(user);
        return user;
    }

    private User setCurrentUser(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authentication = authProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        currentUser.set(user);
        return user;
    }

    public User getCurrentUser() {
        User user = currentUser.get();
        if(user == null) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            user = userDAO.getByEmail(email);
        }
        return user;
    }

    private void validateUser(User user) {
    }
}
