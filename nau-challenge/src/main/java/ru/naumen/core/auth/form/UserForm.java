package ru.naumen.core.auth.form;

/**
 * Login form
 * 
 * @author serce
 * @since 28 oct. 2013 г.
 */
public class UserForm {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
