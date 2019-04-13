package com.example.codehsu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.codehsu.LoginRegister.LoginActivity;
import com.example.codehsu.LoginRegister.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        FirebaseFirestore fsDatabase = FirebaseFirestore.getInstance();

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Continue to home screen
        } else {
            // No user is signed in.
            Button btnGoRegister = findViewById(R.id.btnGoRegister);
            Button btnGoLogin = findViewById(R.id.btnGoLogin);

            btnGoRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }
            });

            btnGoLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }
            });
        }
    }
}
