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

import com.aliferous.mujunified.MenuActivity;
import com.aliferous.mujunified.R;

import java.util.List;

import Model.Categories;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{
    private Context mContext;
    private List<Categories> categoriesList;
    private String Res_Name;




    public CategoriesAdapter(Context mContext, List<Categories> categoriesList, String Res_Name) {
        this.categoriesList = categoriesList;
        this.mContext = mContext;
        this.Res_Name = Res_Name;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.restaurant_item,parent,false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesAdapter.ViewHolder holder, int position) {


        final Categories categories = categoriesList.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);

        holder.cat_Name.setText(categories.getName().toString());

        holder.cat_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                intent.putExtra("Restaurant Name",Res_Name);
                intent.putExtra("Category Name",categories.getName());
                mContext.startActivity(intent);
            }
        });


    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cat_Name;
        public ConstraintLayout cat_Layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_Layout = itemView.findViewById(R.id.Restaurant_Item_Layout);
            cat_Name = itemView.findViewById(R.id.Restaurant_Item_Category);

        }
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}