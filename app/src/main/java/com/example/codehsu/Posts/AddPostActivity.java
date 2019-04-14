package com.example.codehsu.Posts;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codehsu.Collections.Post;
import com.example.codehsu.Collections.User;
import com.example.codehsu.HomeActivity;
import com.example.codehsu.MainActivity;
import com.example.codehsu.ProfileActivity;
import com.example.codehsu.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddPostActivity extends AppCompatActivity {

    final String TAG = "AddPostActivity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        User thisUser = new User();
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        thisUser.getUser(mAuth.getUid(), new User.UserCallback() {
            @Override
            public void onCallback(User thisUser) {
                if (! thisUser.is_business) {
                    Toast.makeText(AddPostActivity.this, "Only businesses can create posts",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(AddPostActivity.this, HomeActivity.class);
                    AddPostActivity.this.startActivity(myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                }
            }
        });

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Post postInstance = new Post();

        postInstance.getAllPosts(new Post.AllPostsCallback() {
            @Override
            public void onCallback(ArrayList<Post> allPosts) {
                // Do stuff with allPosts here
                Log.d("testdata", allPosts.toString());
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

                if (etTitle.getText().toString().equals("")) {
                    etTitle.setHintTextColor(Color.RED);
                    return;
                }
                if (etPitch.getText().toString().equals("")) {
                    etPitch.setHintTextColor(Color.RED);
                    return;
                }

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

                Toast.makeText(getApplicationContext(),"Submitted!",Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(AddPostActivity.this, AddPostActivity.class);
                AddPostActivity.this.startActivity(myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
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

    private void postInstance(String s, Post.MyCallback tag) {
    }
}