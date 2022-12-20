package com.example.my_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.my_task.Adapter.ProductAdapter;
import com.example.my_task.Response.AllProduct;
import com.example.my_task.Response.RetrofitModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView btnBack;
    FrameLayout btnCart;
    SearchView search;
    List<AllProduct> allProducts;
    ProductAdapter productAdapter;


    GridLayoutManager gridLayoutManager;


    int pageCount=1;
    private int perPage=6;
    NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        btnBack=findViewById(R.id.backBtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCart=findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });


        search = findViewById(R.id.search);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                searchv(s);
                return true;
            }
        });

        nestedScrollView=findViewById(R.id.nestedScrollView);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        fetchData(pageCount);
        setUpPagination(true);

    }

    private void setUpPagination(boolean isPaginationAllowed) {

        if (isPaginationAllowed){
            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                    fetchData(++pageCount);
                    Toast.makeText(this, ""+pageCount, Toast.LENGTH_SHORT).show();
                }else {
                    nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v1, scrollX1, scrollY1, oldScrollX1, oldScrollY1) -> {
                    });
                }
            });
        }

    }

    private void fetchData(int pageCount) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitModel retrofitModel=retrofit.create(RetrofitModel.class);

        Call<List<AllProduct>> call=retrofitModel.getProduct(pageCount,perPage);

        call.enqueue(new Callback<List<AllProduct>>() {
            @Override
            public void onResponse(Call<List<AllProduct>> call, Response<List<AllProduct>> response) {


                allProducts=response.body();
                productAdapter=new ProductAdapter(ProductActivity.this,allProducts);
                recyclerView.setAdapter(productAdapter);



            }

            @Override
            public void onFailure(Call<List<AllProduct>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void searchv(String str) {
        List<AllProduct> allProductList1= new ArrayList<>();
        for (AllProduct object : allProducts) {
            if (object.getTitle().toLowerCase().contains(str.toLowerCase())) {
                allProductList1.add(object);
            }
        }
        productAdapter=new ProductAdapter(ProductActivity.this,allProductList1);
        recyclerView.setAdapter(productAdapter);
    }
}