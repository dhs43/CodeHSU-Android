package com.example.codehsu.Posts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.codehsu.Collections.Post;
import com.example.codehsu.R;

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


        tvTitle.setText(getIntent().getExtras().getString("title"));
        tvPitch.setText(getIntent().getExtras().getString("pitch"));
        tvDescription.setText(getIntent().getExtras().getString("description"));
        tvKeywords.setText(getIntent().getExtras().getString("keywords"));
        tvDifficulty.setText(getIntent().getExtras().getString("difficulty"));
        tvCompensation.setText(getIntent().getExtras().getString("compensation"));
    }
}
