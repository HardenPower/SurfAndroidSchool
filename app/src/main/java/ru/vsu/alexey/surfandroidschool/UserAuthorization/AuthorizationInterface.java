package ru.vsu.alexey.surfandroidschool.UserAuthorization;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;




public class AuthorizationInterface {

    @POST("/auth/login")
    public Call<LoginResponse> postData (@Body LoginRequest data);

}
