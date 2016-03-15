package com.rutgersanonymous.rutgersanonymous.data;

import java.io.Serializable;

/**
 * Created by mdislam on 3/13/16.
 */
public class SavedSession implements Serializable {

    private String id;
    private String username;
    private String email;
    private String password;
    private String campus;
    private String num_posts;
    private String rank;


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

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getNum_posts() {
        return num_posts;
    }

    public void setNum_posts(String num_posts) {
        this.num_posts = num_posts;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}

