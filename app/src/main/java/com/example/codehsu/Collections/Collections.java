package com.example.codehsu.Collections;

import java.util.ArrayList;

public class Collections {
    ArrayList<User> allUsers;
    ArrayList<Post> allPosts;
    ArrayList<Comment> postComments;

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Post> getAllPosts() {
        return allPosts;
    }

    public ArrayList<Comment> getPostComments() {
        return postComments;
    }
}
