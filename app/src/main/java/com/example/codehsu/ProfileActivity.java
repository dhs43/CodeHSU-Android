package com.example.codehsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codehsu.Collections.Post;
import com.example.codehsu.Collections.User;
import com.example.codehsu.LoginRegister.LoginActivity;
import com.example.codehsu.LoginRegister.RegisterActivity;
import com.example.codehsu.Posts.AddPostActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public FirebaseAuth mAuth;

    TextView tvName;
    TextView tvAddress;
    TextView tvEmail;
    TextView tvPhone;
    TextView tvBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvBio = findViewById(R.id.tvBio);


        User thisUser = new User();
        mAuth = FirebaseAuth.getInstance();
        thisUser.getUser(mAuth.getUid(), new User.UserCallback() {
            @Override
            public void onCallback(User thisUser) {
                tvName.setText(thisUser.name);
                tvAddress.setText(thisUser.address);
                tvEmail.setText(thisUser.email);
                tvPhone.setText(thisUser.phone);
                tvBio.setText(thisUser.bio);
            }
        });

        tvName.setText(thisUser.name);
        tvAddress.setText(thisUser.address);
        tvEmail.setText(thisUser.email);
        tvPhone.setText(thisUser.phone);
        tvBio.setText(thisUser.bio);

        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                Intent myIntent = new Intent(ProfileActivity.this, MainActivity.class);
                ProfileActivity.this.startActivity(myIntent);
            }
        });


        bottomNavigationView = findViewById(R.id.bottom_navigation_profile);
        bottomNavigationView.getMenu().findItem(R.id.action_profile).setChecked(true);

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
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        return true;
                    case R.id.action_compose:
                        Log.d("Menu clicked: ", "home");
                        intent = new Intent(getApplicationContext(), AddPostActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        return true;
                    case R.id.action_profile:
                        Log.d("Menu clicked: ", "home");
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        return true;
                    default:
                        return true;
                }
            }
        });

    }
}
