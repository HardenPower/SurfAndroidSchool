package ru.vsu.alexey.surfandroidschool.UserAuthorization;


import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("userInfo")
    private userInfo userInfo;




    public String getAccessToken(){
        return this.accessToken;
    }
    public int getId(){ return this.userInfo.id; }

    public String getUsername(){
        return this.userInfo.username;
    }
    public String getFirstName() {return this.userInfo.firstName; }
    public String getLastName() {return this.userInfo.lastName; }
    public String getUserDescription() {return this.userInfo.userDescription; }


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






}
