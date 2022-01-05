package com.aliferous.mujunified;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapter.CategoriesAdapter;
import Adapter.RestaurantListAdapter;
import Model.Categories;
import Model.Restaurants;

public class RestaurantActivity extends AppCompatActivity {

    Intent intent;
    String Name;

    RecyclerView res_recyclerView;
    private CategoriesAdapter cat_Adapter;
    List<Categories> categoriesList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        intent = getIntent();
        Name = intent.getStringExtra("Name");


        db = FirebaseFirestore.getInstance();


        res_recyclerView = findViewById(R.id.Restaurant_RecyclerView);
        res_recyclerView.setHasFixedSize(true);
        res_recyclerView.setLayoutManager(new LinearLayoutManager(RestaurantActivity.this));

        categoriesList = new ArrayList<>();
        readCategories();

    }

    private void readCategories() {

        db = FirebaseFirestore.getInstance();

        db.collection("Restaurants")
                .document(Name)
                .collection("Menu")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        categoriesList.clear();
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            Categories categories = dc.getDocument().toObject(Categories.class);


                            if (categories!=null && categories.getName() != null ) {
                                categoriesList.add(categories);
                            }


                        }

                        cat_Adapter = new CategoriesAdapter(RestaurantActivity.this, categoriesList,Name);
                        res_recyclerView.setAdapter(cat_Adapter);

                    }
                });
    }
}