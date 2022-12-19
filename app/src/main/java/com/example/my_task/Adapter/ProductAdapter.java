package com.example.my_task.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.my_task.CartActivity;
import com.example.my_task.CartModel.AddModel;
import com.example.my_task.CartModel.ProductCartModel;
import com.example.my_task.CartModel.ProductModel;
import com.example.my_task.R;
import com.example.my_task.Response.AllProduct;
import com.example.my_task.Response.RetrofitModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Glide.with(context).load(allProduct.getImage()).into(holder.productImage);
        holder.textViewTitle.setText(allProduct.getTitle());
        holder.textViewPrice.setText(String.valueOf(allProduct.getPrice()));
    }

    @Override
    public int getItemCount() {
        return allProductList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewTitle,textViewPrice;
        ImageView productImage,addCartBtn;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.titleProduct);
            productImage=itemView.findViewById(R.id.productImage_ID);
            textViewPrice=itemView.findViewById(R.id.productPrize_ID);
            addCartBtn=itemView.findViewById(R.id.addCharBtn_ID);

            addCartBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            AllProduct allProduct=allProductList.get(getAdapterPosition());

            int id=allProduct.getId();
           // Toast.makeText(context, "id: "+id, Toast.LENGTH_SHORT).show();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ProductCartModel productCartModel=new ProductCartModel(id,1);
            AddModel addModel=new AddModel(id,"3","2020 - 02 - 03",productCartModel);

            RetrofitModel retrofitModel=retrofit.create(RetrofitModel.class);
            Call<ProductModel> call=retrofitModel.GetCart(addModel);

            call.enqueue(new Callback<ProductModel>() {
                @Override
                public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(context, "Add Cart Successfully", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ProductModel> call, Throwable t) {
                    Toast.makeText(context, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
