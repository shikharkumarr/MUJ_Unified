package Adapter;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.aliferous.mujunified.R;
import com.aliferous.mujunified.RestaurantActivity;

import java.util.List;

import Model.Restaurants;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>{
    private Context mContext;
    private List<Restaurants> restaurantsList;




    public RestaurantListAdapter(Context mContext, List<Restaurants> restaurantsList) {
        this.restaurantsList = restaurantsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.food_item,parent,false);
        return new RestaurantListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final Restaurants restaurants = restaurantsList.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);

        holder.res_Name.setText(restaurants.getName().toString());
        holder.res_Contact.setText(String.valueOf(restaurants.getContact()));

        holder.res_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, RestaurantActivity.class);
                intent.putExtra("Name", restaurants.getName());
                mContext.startActivity(intent);

            }
        });




    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout res_Layout;
        public TextView res_Name,res_Contact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            res_Name = itemView.findViewById(R.id.Food_Item_Name);
            res_Contact = itemView.findViewById(R.id.Food_Item_Contact);
            res_Layout = itemView.findViewById(R.id.Food_Item_Layout);

        }
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }
}