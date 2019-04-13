package com.example.codehsu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.codehsu.Collections.Post;

import java.util.ArrayList;

public class AddPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etTitle = findViewById(R.id.etTitle);
                EditText etPitch = findViewById(R.id.etPitch);
                EditText etDescription = findViewById(R.id.etDescription);
                EditText etDifficulty = findViewById(R.id.etDifficulty);
                EditText etCompensation = findViewById(R.id.etCompensation);

                String title = etTitle.getText().toString();
                String pitch = etPitch.getText().toString();
                String description = etDescription.getText().toString();
                String difficulty = etDifficulty.getText().toString();
                String compensation = etCompensation.getText().toString();

                Post newPost = new Post();
                String testLink = "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png";
                ArrayList<String> testTags = new ArrayList<String>();
                testTags.add("PHP");
                testTags.add("HTML");
                testTags.add("CSS");

                newPost.createPost(title, pitch, description, testLink, difficulty, testTags, compensation);
            }
        });
    }
}
