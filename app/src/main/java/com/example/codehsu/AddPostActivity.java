package com.example.codehsu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.codehsu.Collections.Post;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddPostActivity extends AppCompatActivity {

    final String TAG = "AddPostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Post postInstance = new Post();

        postInstance.getPost("4R6qoWHklukShNfgvvwD", new Post.MyCallback() {
            @Override
            public void onCallback(Post thisPost) {
                Log.d(TAG, thisPost.toString());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();;
                String auth_id = mAuth.getCurrentUser().getUid();

                EditText etTitle = findViewById(R.id.etTitle);
                EditText etPitch = findViewById(R.id.etPitch);
                EditText etDescription = findViewById(R.id.etDescription);
                EditText etDifficulty = findViewById(R.id.etDifficulty);
                EditText etCompensation = findViewById(R.id.etCompensation);
                EditText etTags = findViewById(R.id.etTags);

                String title = etTitle.getText().toString();
                String pitch = etPitch.getText().toString();
                String description = etDescription.getText().toString();
                String difficulty = etDifficulty.getText().toString();
                String compensation = etCompensation.getText().toString();
                String unparsedTags = etTags.getText().toString();

                List<String> tags = Arrays.asList(unparsedTags.split(",[ ]*"));

                Post newPost = new Post();
                String testLink = "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png";

                newPost.createPost(auth_id, title, pitch, description, testLink, difficulty, tags, compensation);
            }
        });
    }

    private void postInstance(String s, Post.MyCallback tag) {
    }
}