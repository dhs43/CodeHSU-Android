package com.example.codehsu.Collections;

import java.util.ArrayList;

public class Post {
    private String title;
    private String pitch;
    private String description;
    private String picture_link;
    private String difficulty;
    private ArrayList<String> tags;
    private String compensation;
    private String post_id;
    private ArrayList<Comment> posts;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public ArrayList<Comment> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Comment> posts) {
        this.posts = posts;
    }
}
