package com.example.truyenol.model;

public class Comment {
    private int id;
    private String comment;
    User user ;

    public Comment() {
    }

    public Comment(int id, String comment, User user) {
        this.id = id;
        this.comment = comment;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
