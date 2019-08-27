package ru.vsu.alexey.surfandroidschool.UserAuthorization;


import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("userInfo")
    private userInfo userInfo;


    private class userInfo{
        @SerializedName("id")
        int id;

        @SerializedName("username")
        private String username;

        @SerializedName("firstName")
        private String firstName;

        @SerializedName("lastName")
        private String lastName;

        @SerializedName("userDescription")
        private String userDescription;

    }


    public String getAccessToken(){
        return this.accessToken;
    }

    public String getUsername(){
        return this.userInfo.username;
    }



}
