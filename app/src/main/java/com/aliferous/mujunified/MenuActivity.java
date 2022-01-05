package com.aliferous.mujunified;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapter.CategoriesAdapter;
import Adapter.MenuAdapter;
import Model.Categories;
import Model.Products;

public class MenuActivity extends AppCompatActivity {

    Intent intent;
    String Res_Name,Cat_Name;

    RecyclerView menu_recyclerView;
    private MenuAdapter menu_Adapter;
    List<Products> productsList;
    FirebaseFirestore db;
    Button Cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        intent = getIntent();

        Res_Name = intent.getStringExtra("Restaurant Name");
        Cat_Name = intent.getStringExtra("Category Name");


        db = FirebaseFirestore.getInstance();


        menu_recyclerView = findViewById(R.id.Menu_RecyclerView);
        menu_recyclerView.setHasFixedSize(true);
        menu_recyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this));

        productsList = new ArrayList<>();
        readCategories();

    }

    private void readCategories() {

        db = FirebaseFirestore.getInstance();

        db.collection("Restaurants")
                .document(Res_Name)
                .collection("Menu")
                .document(Cat_Name)
                .collection("Collection")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        productsList.clear();
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            Products products = dc.getDocument().toObject(Products.class);


                            if (products!=null && products.getName() != null ) {
                                productsList.add(products);
                            }


                        }

                        menu_Adapter = new MenuAdapter(MenuActivity.this, productsList);
                        menu_recyclerView.setAdapter(menu_Adapter);

                    }
                });
    }

}