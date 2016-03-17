package com.rutgersanonymous.rutgersanonymous.data;

import java.io.Serializable;

/**
 * Created by mdislam on 3/13/16.
 */
public class SavedSession implements Serializable {

    private String username;
    private String password;


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



}

