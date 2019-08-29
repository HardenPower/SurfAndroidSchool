package ru.vsu.alexey.surfandroidschool.repository;

import android.util.Log;

import retrofit2.Call;
import ru.vsu.alexey.surfandroidschool.SharedPreferencesUtil.SharedPreferencesUtil;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginRequest;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginResponse;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.NetworkService;
import ru.vsu.alexey.surfandroidschool.contract.LoginContract;

public class LoginRepository implements LoginContract.Repository {
    private static final String TAG = "MainRepository";

    @Override
    public Call<LoginResponse> loginUserRequest(LoginRequest loginRequest) {
        Log.d(TAG, "loadMessage()");
        return NetworkService.getInstance().getAuthorizationInterface().postData(loginRequest);
    }

    @Override
    public void saveUserInfo(LoginResponse loginResponse) {
        SharedPreferencesUtil.putString(SharedPreferencesUtil.TOKEN, loginResponse.getAccessToken());
        SharedPreferencesUtil.putInt(SharedPreferencesUtil.ID, loginResponse.getId());
        SharedPreferencesUtil.putString(SharedPreferencesUtil.USERNAME, loginResponse.getUsername());
        SharedPreferencesUtil.putString(SharedPreferencesUtil.FIRSTNAME, loginResponse.getFirstName());
        SharedPreferencesUtil.putString(SharedPreferencesUtil.LASTNAME, loginResponse.getLastName());
        SharedPreferencesUtil.putString(SharedPreferencesUtil.USER_DESCRIPTION, loginResponse.getUserDescription());
    }

}
