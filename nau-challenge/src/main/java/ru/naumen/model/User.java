package ru.naumen.model;

import ru.naumen.core.auth.accessKey.AccessKeyGenerator;

/**
 * Пользователь системы
 * 
 * @author serce
 * @since 25 окт. 2013 г.
 */
public class User {
    private long id;

    private String email;
    private String fio;
    private String password;
    private String accessKey;

    User() {
    }

    public static User create(String email, String fio, String password) {
        User user = new User();
        user.setEmail(email);
        user.setFio(fio);
        user.setPassword(password);
        user.setAccessKey(AccessKeyGenerator.generateNewAccessKey());
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    
    @Override
    public int hashCode() {
        return (int) (id + id << 31);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        return id == ((User) obj).getId();
    }
}
