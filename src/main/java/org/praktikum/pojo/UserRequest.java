package org.praktikum.pojo;

public class UserRequest {
    private String name;
    private String password;
    private String email;

    public UserRequest(String name, String password, String email) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserRequest(String password, String email) {
        this.email = email;
        this.password = password;
    }

    public UserRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
