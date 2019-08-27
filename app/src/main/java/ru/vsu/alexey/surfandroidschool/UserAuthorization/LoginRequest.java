package ru.vsu.alexey.surfandroidschool.UserAuthorization;

import com.google.gson.annotations.*;

public class LoginRequest {

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;


    public String getlogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }


}
