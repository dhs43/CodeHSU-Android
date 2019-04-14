package com.example.codehsu.Collections;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class User {

    final String TAG = "PostClass";

    public String name;
    public String address;
    public String phone;
    public String email;
    public String bio;
    public ArrayList<String> courses_completed;
    public String avatar_url;
    public String resume_url;
    public boolean is_business;
    public ArrayList<Post> posts;
    public boolean verified;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getUser(String auth_id, final UserCallback myCallback) {
        DocumentReference docRef = db.collection("users").document(auth_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> data = document.getData();
                        User thisUser = new User();
                        thisUser.address = data.get("address").toString();
                        thisUser.bio = data.get("bio").toString();
                        thisUser.email = data.get("email").toString();
                        thisUser.name = data.get("name").toString();
                        thisUser.phone = data.get("phone").toString();

                        myCallback.onCallback(thisUser);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public interface UserCallback {
        void onCallback(User thisUser);

    }
}
