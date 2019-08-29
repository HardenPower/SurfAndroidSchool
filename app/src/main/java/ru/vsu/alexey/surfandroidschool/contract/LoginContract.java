package ru.vsu.alexey.surfandroidschool.contract;

import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginRequest;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginResponse;


import retrofit2.Call;


public interface LoginContract {

    interface View {
        void showError();

        void hideProgress();

        void showProgress();

        void showSnackbar(String message);

        void showLoginError(String message);

        void showPasswordError(String message);

        String getLogin();

        String getPassword();

        void openMemesScreen();
    }

    interface Presenter {
        void onButtonWasClicked();

        void onDestroy();
    }

    interface Repository {
        Call<LoginResponse> loginUserRequest(LoginRequest loginRequest);

        void saveUserInfo(LoginResponse loginResponse);
    }


}