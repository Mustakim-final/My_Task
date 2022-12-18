package com.example.my_task.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_task.R;
import com.example.my_task.Response.AllProduct;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder>{
    Context context;
    List<AllProduct> allProductList;

    public ProductAdapter(Context context, List<AllProduct> allProductList) {
        this.context = context;
        this.allProductList = allProductList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        AllProduct allProduct=allProductList.get(position);
        holder.textViewTitle.setText(allProduct.getTitle());
    }

    @Override
    public int getItemCount() {
        return allProductList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.titleProduct);
        }
    }
}
