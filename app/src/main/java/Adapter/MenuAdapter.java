package Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.aliferous.mujunified.MenuActivity;
import com.aliferous.mujunified.R;

import java.util.List;

import Model.Categories;
import Model.Products;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{
    private Context mContext;
    private List<Products> productsList;




    public MenuAdapter(Context mContext, List<Products> productsList) {
        this.productsList = productsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item,parent,false);
        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuAdapter.ViewHolder holder, int position) {


        final Products products = productsList.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);

        holder.menu_Name.setText(products.getName().toString());
        holder.menu_Amount.setText(String.valueOf(products.getAmount()));

        holder.menu_AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "working", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView menu_Name,menu_Amount;
        public Button menu_AddToCart;
        public ConstraintLayout menu_Layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menu_Name = itemView.findViewById(R.id.Product_Item_Name);
            menu_Amount = itemView.findViewById(R.id.Product_Item_Amount);
            menu_AddToCart = itemView.findViewById(R.id.Product_Item_AddtoCart);
            menu_Layout = itemView.findViewById(R.id.Product_Item_Layout);

        }
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}