package com.example.codehsu.LoginRegister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.codehsu.Posts.AddPostActivity;
import com.example.codehsu.MainActivity;
import com.example.codehsu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivity";
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etUsername = findViewById(R.id.etUsername);
                EditText etNewPassword = findViewById(R.id.etNewPassword);
                RadioButton radioBusiness = findViewById(R.id.radioBusiness);
                RadioButton radioStudent = findViewById(R.id.radioStudent);
                EditText etAddress = findViewById(R.id.etAddress);
                EditText etPhone = findViewById(R.id.etPhone);
                EditText etNewEmail = findViewById(R.id.etNewEmail);
                EditText etBio = findViewById(R.id.etBio);

                String email = etUsername.getText().toString();
                String password = etNewPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }


                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    // TODO: Add the rest of the data to the database

                                    Intent myIntent = new Intent(RegisterActivity.this, AddPostActivity.class);
                                    RegisterActivity.this.startActivity(myIntent);
                                    finish();
                                } else {
                                    // Go back to register/login screen
                                    Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                    RegisterActivity.this.startActivity(myIntent);
                                }
                            }
                        });
            }
        });
    }
}
