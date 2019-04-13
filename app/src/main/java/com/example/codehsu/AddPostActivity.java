package com.example.codehsu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.codehsu.Collections.Post;

import java.util.ArrayList;

public class AddPostActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

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

        bottomNavigationView = findViewById(R.id.bottom_navigation_add_post);
        bottomNavigationView.getMenu().findItem(R.id.action_compose).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()

        {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){
                Intent intent;

                Log.d("Menu clicked: ", Integer.toString(item.getItemId()));
                switch (item.getItemId()) {
                    case R.id.action_view:
                        Log.d("Menu clicked: ", "home");
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));                        return true;
                    case R.id.action_compose:
                        Log.d("Menu clicked: ", "home");
                        intent = new Intent(getApplicationContext(), AddPostActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));                        return true;
                    case R.id.action_profile:
                        Log.d("Menu clicked: ", "home");
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));                        return true;
                    default:
                        return true;
                }
            }
        });
    }
}
