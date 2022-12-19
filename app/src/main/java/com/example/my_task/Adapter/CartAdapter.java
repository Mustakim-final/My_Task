package com.example.my_task.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_task.CartModel.ProductCartModel;
import com.example.my_task.R;
import com.example.my_task.Response.RetrofitModel;
import com.example.my_task.ShowCartModel.CartResponse;
import com.example.my_task.ShowCartModel.ProductArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.MyHolder>{
    Context context;
    List<CartResponse> cartResponseList;

    public CartAdapter(Context context, List<CartResponse> cartResponseList) {
        this.context = context;
        this.cartResponseList = cartResponseList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CartResponse cartResponse=cartResponseList.get(position);
        holder.priceText.setText(cartResponse.getUserId());

    }

    @Override
    public int getItemCount() {
        return cartResponseList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView quentityText,priceText;
        ImageView btnDelete;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            quentityText=itemView.findViewById(R.id.textQuantity);
            priceText=itemView.findViewById(R.id.textPrice);
            btnDelete=itemView.findViewById(R.id.btnDelete);

            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitModel retrofitModel=retrofit.create(RetrofitModel.class);

            Call<CartResponse> call=retrofitModel.DeleteCart(2);
            call.enqueue(new Callback<CartResponse>() {
                @Override
                public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CartResponse> call, Throwable t) {

                }
            });
        }
    }
}
