package com.example.codehsu;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.codehsu.Collections.Post;
import com.example.codehsu.LoginRegister.RegisterActivity;
import com.example.codehsu.Posts.AddPostActivity;
import com.example.codehsu.Posts.DetailedPostActivity;
import com.example.codehsu.Posts.PostAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.Serializable;

public class HomeActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference postRef = db.collection("posts");

    private PostAdapter adapter;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupRecyclerView();
        
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().findItem(R.id.action_view).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()

        {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item){
                Intent intent;
                Log.d("Menu clicked: ", Integer.toString(item.getItemId()));

                switch (item.getItemId()) {
                    case R.id.action_view:
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        return true;
                    case R.id.action_compose:
                        intent = new Intent(getApplicationContext(), AddPostActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        return true;
                    case R.id.action_profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        return true;
                    default:
                        return true;
                }
            }
        });

        adapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Post thisPost = documentSnapshot.toObject(Post.class);

                Intent myIntent = new Intent(HomeActivity.this, DetailedPostActivity.class);
                myIntent.putExtra("title", thisPost.title);
                myIntent.putExtra("pitch", thisPost.pitch);
                myIntent.putExtra("description", thisPost.description);
                if (thisPost.tags != null) {
                    String tags = thisPost.tags.toString().substring(1, thisPost.tags.toString().length()-1);
                    myIntent.putExtra("keywords", tags);
                }
                myIntent.putExtra("difficulty", thisPost.difficulty);
                myIntent.putExtra("compensation", thisPost.compensation);
                HomeActivity.this.startActivity(myIntent);
            }
        });
    }

    private void setupRecyclerView() {
        Query query = postRef;

        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query, Post.class)
                .build();

        adapter = new PostAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
