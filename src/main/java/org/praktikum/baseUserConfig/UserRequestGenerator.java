package org.praktikum.baseUserConfig;

import org.praktikum.pojo.UserRequest;

public class UserRequestGenerator {
    public static UserRequest getUserRequest(String name, String email, String password) {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setPassword(password);
        userRequest.setName(name);
        return userRequest;
    }
}
