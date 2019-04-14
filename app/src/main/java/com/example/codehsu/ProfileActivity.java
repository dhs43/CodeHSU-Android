package com.example.codehsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class ProfileActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



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
