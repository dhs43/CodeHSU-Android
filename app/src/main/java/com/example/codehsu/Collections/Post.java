package com.example.codehsu.Collections;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Post {
    public String title;
    public String pitch;
    public String description;
    public String picture_link;
    public String difficulty;
    public ArrayList<String> tags;
    public String compensation;
    public ArrayList<Comment> posts;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void createPost(String title, String pitch, String description, String picture_link,
                           String difficulty, ArrayList<String> tags, String compensation) {

        Post newPost = new Post();
        newPost.title = title;
        newPost.pitch = pitch;
        newPost.description = description;
        newPost.picture_link = picture_link;
        newPost.difficulty = difficulty;
        newPost.tags = tags;
        newPost.compensation = compensation;

        db.collection("posts")
                .add(newPost)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("createPost", "Sucessfully created post");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("createPost", "createPost failed: " + e.getMessage());
                    }
                });
    }

    public void updatePost(String post_id) {

    }

    public void getPost(String post_id) {

    }
}
