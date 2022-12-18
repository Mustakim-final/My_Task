package com.example.my_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.my_task.Adapter.ProductAdapter;
import com.example.my_task.Response.AllProduct;
import com.example.my_task.Response.RetrofitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitModel retrofitModel=retrofit.create(RetrofitModel.class);

        Call<List<AllProduct>> call=retrofitModel.getProduct();

        call.enqueue(new Callback<List<AllProduct>>() {
            @Override
            public void onResponse(Call<List<AllProduct>> call, Response<List<AllProduct>> response) {


                List<AllProduct> allProducts=response.body();
                ProductAdapter productAdapter=new ProductAdapter(ProductActivity.this,allProducts);
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<AllProduct>> call, Throwable t) {

            }
        });
    }
}