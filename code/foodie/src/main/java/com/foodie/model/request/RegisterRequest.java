package com.foodie.model.request;

import org.springframework.stereotype.Component;

/**
 * @author jim.wu
 * @date 11/25/13
 */
@Component
public class RegisterRequest {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
