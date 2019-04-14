package com.example.codehsu.Collections;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Post {

    final String TAG = "PostClass";

    public String auth_id;
    public String title;
    public String pitch;
    public String description;
    public String picture_link;
    public String difficulty;
    public List<String> tags;
    public String compensation;
    public ArrayList<Comment> comments;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void createPost(String auth_id, String title, String pitch, String description, String picture_link,
                           String difficulty, List<String> tags, String compensation) {

        Post newPost = new Post();
        newPost.auth_id = auth_id;
        newPost.title = title;
        newPost.pitch = pitch;
        newPost.description = description;
        newPost.picture_link = picture_link;
        newPost.difficulty = difficulty;
        newPost.tags = tags;
        newPost.compensation = compensation;

        db.collection("posts")
                .add(newPost)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("createPost", "Successfully created post");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("createPost", "createPost failed: " + e.getMessage());
                    }
                });
    }

    public void updatePost(String post_id) {

    }

    public void getPost(String post_id, final MyCallback myCallback) {
        DocumentReference docRef = db.collection("posts").document(post_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        Map<String, Object> data = document.getData();
                        Post thisPost = new Post();
                        thisPost.difficulty = data.get("difficulty").toString();
                        thisPost.auth_id = data.get("auth_id").toString();
                        thisPost.description = data.get("description").toString();
                        thisPost.compensation = data.get("compensation").toString();
                        thisPost.pitch = data.get("pitch").toString();
                        thisPost.title = data.get("title").toString();
                        String unparsedTags = data.get("tags").toString();
                        unparsedTags = unparsedTags.substring(1, unparsedTags.length()-1);
                        thisPost.tags = Arrays.asList(unparsedTags.split(", "));
                        myCallback.onCallback(thisPost);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                        Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public interface MyCallback {
        void onCallback(Post thisPost);
    }


    public void getAllPosts(final AllPostsCallback myCallback) {
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Post> allPosts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                Map<String, Object> data = document.getData();
                                Post thisPost = new Post();
                                if(data.get("difficulty").toString() != null) {
                                    thisPost.difficulty = data.get("difficulty").toString();
                                }
                                if(data.get("auth_id").toString() != null) {
                                    thisPost.auth_id = data.get("auth_id").toString();
                                }
                                if(data.get("description").toString() != null) {
                                    thisPost.description = data.get("description").toString();
                                }
                                if(data.get("compensation").toString() != null) {
                                    thisPost.compensation = data.get("compensation").toString();
                                }
                                if(data.get("pitch").toString() != null) {
                                    thisPost.pitch = data.get("pitch").toString();
                                }
                                if(data.get("pitch").toString() != null) {
                                    thisPost.title = data.get("title").toString();
                                }
                                if(data.get("unparsedTags") != null) {
                                    String unparsedTags = data.get("tags").toString();
                                    unparsedTags = unparsedTags.substring(1, unparsedTags.length()-1);
                                    thisPost.tags = Arrays.asList(unparsedTags.split(", "));
                                }

                                allPosts.add(thisPost);
                            }
                            myCallback.onCallback(allPosts);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public interface AllPostsCallback {
        void onCallback(ArrayList<Post> allPosts);
    }
}
