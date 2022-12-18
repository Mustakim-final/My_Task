package com.example.my_task.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitModel {

    @GET("products")
    Call<List<AllProduct>> getProduct();

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> GetLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}
