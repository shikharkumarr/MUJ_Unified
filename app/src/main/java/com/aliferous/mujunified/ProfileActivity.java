package com.aliferous.mujunified;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.Document;

import Model.Users;

public class ProfileActivity extends AppCompatActivity {

    TextView Name,Course,YearSem;

    FirebaseUser firebaseUser;
    FirebaseFirestore reference = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Name = findViewById(R.id.Profile_tv_name);
        Course = findViewById(R.id.Profile_tv_Course);
        YearSem = findViewById(R.id.Profile_tv_YearSem);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String UserId = firebaseUser.getUid();

        DocumentReference docRef = reference.collection("Users").document(UserId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    Users user = document.toObject(Users.class);

                    if (document.exists()) {
                        Toast.makeText(ProfileActivity.this, "Exists", Toast.LENGTH_SHORT).show();

                        assert user != null;
                        Name.setText(user.getUsername());
                        Course.setText(user.getCourse());
                        YearSem.setText(user.getYear());

                    } else {
                        Toast.makeText(ProfileActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, "get failed with" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}