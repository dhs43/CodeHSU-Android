package com.example.codehsu.Posts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codehsu.Collections.Post;
import com.example.codehsu.HomeActivity;
import com.example.codehsu.MainActivity;
import com.example.codehsu.ProfileActivity;
import com.example.codehsu.R;
import com.google.firebase.auth.FirebaseAuth;

public class DetailedPostActivity extends AppCompatActivity {

    public TextView tvTitle;
    public TextView tvPitch;
    public TextView tvKeywords;
    public TextView tvDescription;
    public TextView tvDifficulty;
    public TextView tvCompensation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        tvTitle = findViewById(R.id.tvTitle);
        tvPitch = findViewById(R.id.tvPitch);
        tvKeywords = findViewById(R.id.tvKeywords);
        tvDescription = findViewById(R.id.tvDescription);
        tvDifficulty = findViewById(R.id.tvDifficulty);
        tvCompensation = findViewById(R.id.tvCompensation);

        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailedPostActivity.this, HomeActivity.class);
                DetailedPostActivity.this.startActivity(myIntent);
            }
        });

        if (getIntent().getExtras().getString("title") != "null") {
            tvTitle.setText(getIntent().getExtras().getString("title"));
        }
        if (getIntent().getExtras().getString("pitch") != "null") {
            tvPitch.setText("Pitch: " + getIntent().getExtras().getString("pitch"));
        }
        if ((getIntent().getExtras().getString("description") != "null")) {
            tvDescription.setText("Desc: " + getIntent().getExtras().getString("description"));
        }
        if (getIntent().getExtras().getString("keywords") != "null") {
            tvKeywords.setText("Keywords: " + getIntent().getExtras().getString("keywords"));
        }
        if (getIntent().getExtras().getString("difficulty") != "null") {
            tvDifficulty.setText("Difficulty: " +getIntent().getExtras().getString("difficulty"));
        }
        if (getIntent().getExtras().getString("compensation") != "null") {
            tvCompensation.setText("Compensation: " + getIntent().getExtras().getString("compensation"));
        }
    }
}
