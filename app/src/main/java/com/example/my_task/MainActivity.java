package com.example.my_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_task.Response.LoginResponseModel;
import com.example.my_task.Response.RetrofitModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText usernamelEdit,passwordEdit;

    private Button button;
    private TextView textView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        usernamelEdit=findViewById(R.id.loginUsername_ID);
        passwordEdit=findViewById(R.id.loginPassword_ID);
        button=findViewById(R.id.loginBtn_ID);
        textView=findViewById(R.id.goSignUp_ID);
        progressBar=findViewById(R.id.signProgressBar_ID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernamelEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                signInUser(username,password);

            }
        });
    }

    private void signInUser(String username, String password) {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitModel retrofitModel=retrofit.create(RetrofitModel.class);
        Call<LoginResponseModel> call=retrofitModel.GetLogin("mor_2314","83r5^_");
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()){
                    Intent intent=new Intent(MainActivity.this,ProductActivity.class);
                    startActivity(intent);
                }


                Toast.makeText(MainActivity.this, ""+response.body().getToken(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}