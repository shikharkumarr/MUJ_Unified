package com.aliferous.mujunified;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

import Adapter.RestaurantListAdapter;
import Model.Restaurants;

public class FoodActivity extends AppCompatActivity {

    RecyclerView res_recyclerView;
    List<Restaurants> restaurantsList;
    private RestaurantListAdapter res_Adapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        res_recyclerView = findViewById(R.id.Food_RecyclerView);

        res_recyclerView.setHasFixedSize(true);
        res_recyclerView.setLayoutManager(new LinearLayoutManager(FoodActivity.this));

        restaurantsList = new ArrayList<>();
        readRestaurants();

    }

    private void readRestaurants() {

        db = FirebaseFirestore.getInstance();
        db.collection("Restaurants")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        restaurantsList.clear();
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            Restaurants restaurants = dc.getDocument().toObject(Restaurants.class);


                            if (restaurants!=null && restaurants.getName() != null ) {
                                restaurantsList.add(restaurants);
                            }


                        }

                        res_Adapter = new RestaurantListAdapter(FoodActivity.this, restaurantsList);
                        res_recyclerView.setAdapter(res_Adapter);

                    }
                });

    }
}