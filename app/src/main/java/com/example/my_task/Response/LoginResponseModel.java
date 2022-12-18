package com.example.my_task.Response;

public class LoginResponseModel {
    private String username,password,token;

    public LoginResponseModel() {
    }

    public LoginResponseModel(String username, String password,String token) {
        this.username = username;
        this.password = password;
        this.token=token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
