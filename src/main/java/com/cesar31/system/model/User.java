package com.cesar31.system.model;

/**
 *
 * @author cesar31
 */
public class User {

    private String id;
    private String username;
    private String password;
    private UserType type;

    public User() {
    }

    public User(String id, String username, String password, UserType type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", type=" + type + '}';
    }
}
