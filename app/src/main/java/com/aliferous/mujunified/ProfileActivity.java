package com.aliferous.mujunified;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        reference.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        //reference.collection("Users").document(document.getId()).addSnapshotListener(new );
                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.getException());
                }
            }
        });

    }
}