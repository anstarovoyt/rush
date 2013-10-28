package ru.naumen.model.dao;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import ru.naumen.model.User;

@Component
public class UserDAO {
    
    private final Set<User> db = Collections.newSetFromMap(new ConcurrentHashMap<User, Boolean>()); 
    
    public User saveUser(User user) {
        db.add(user);
        return user;
    }

    public User getByEmail(String email) {
        for(User user : db) {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public User getByAccessKey(String accessKey) {
        for(User user : db) {
            if(user.getAccessKey().equals(accessKey))
                return user;
        }
        return null;               
    }
}
