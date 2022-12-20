package com.example.my_task.Response;

import com.example.my_task.CartModel.AddModel;
import com.example.my_task.CartModel.ProductModel;
import com.example.my_task.ShowCartModel.AllCartItme;
import com.example.my_task.ShowCartModel.CartResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitModel {

    @GET("products")
    Call<List<AllProduct>> getProduct(@Query("pageCount")int pageCount,@Query("perPage")int perPage);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> GetLogin(
            @Field("username") String username,
            @Field("password") String password
    );


    @POST("carts")
    Call<ProductModel> GetCart(@Body AddModel addModel);

    @GET("carts")
    Call<List<CartResponse>> GetCartItem();

    @DELETE("carts/{id}")
    Call<CartResponse> DeleteCart(@Path("id")int id);
}
