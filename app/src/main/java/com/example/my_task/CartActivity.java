package com.example.my_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_task.Adapter.CartAdapter;
import com.example.my_task.CartModel.AddModel;
import com.example.my_task.CartModel.ProductCartModel;
import com.example.my_task.CartModel.ProductModel;
import com.example.my_task.Response.RetrofitModel;
import com.example.my_task.ShowCartModel.AllCartItme;
import com.example.my_task.ShowCartModel.CartResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductCartModel productCartModel=new ProductCartModel(1,2);
        AllCartItme allCartItme=new AllCartItme(2,"4","2022-10-12",productCartModel);

        RetrofitModel retrofitModel=retrofit.create(RetrofitModel.class);
        Call<List<CartResponse>> call=retrofitModel.GetCartItem();

        call.enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CartActivity.this, "Show Cart Item", Toast.LENGTH_SHORT).show();
                    List<CartResponse> cartResponseList=response.body();
                    CartAdapter cartAdapter=new CartAdapter(CartActivity.this,cartResponseList);
                    recyclerView.setAdapter(cartAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {

            }
        });
    }


}