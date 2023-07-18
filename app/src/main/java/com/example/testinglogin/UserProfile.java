package com.example.testinglogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity {

    private DatabaseReference userRef;
    private FirebaseUser user;
    private String userID;

    private Uri uri;


    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        user = FirebaseAuth.getInstance().getCurrentUser();
        userRef = FirebaseDatabase.getInstance("https://testinglogin-ddb67-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        userID = user.getUid();


        final TextView nameTextView = (TextView) findViewById(R.id.nameTV);
        final TextView emailTextView = (TextView) findViewById(R.id.emailTV);
        final ImageView profileImageView =  (ImageView) findViewById(R.id.profileIV);

        //profileImageView = findViewById(R.id.profileIV);

        // Retrieve user details from Firebase Realtime Database
        userRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userProf = snapshot.getValue(Users.class);
                if (userProf != null) {
                    // Update UI with user details

                    String name = userProf.name;
                    String email = userProf.email;
                    //String profile = userProf.profile;

                    nameTextView.setText(name);
                    emailTextView.setText(email);

                    Uri uri = user.getPhotoUrl();

                    Picasso.get().load(uri).into(profileImageView);
                    //profileImageView.setText(profile);

                    // Use an image loading library (e.g., Glide or Picasso) to load the profile picture
                    // into the ImageView
                    /*Glide.with(SecondActivity.this)
                            .load(profile)
                            .into(profileImageView);
                    userProf.setProfile(userProf.getProfileUrl());*/
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
            }
        });

        Button signOut = findViewById(R.id.signout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}