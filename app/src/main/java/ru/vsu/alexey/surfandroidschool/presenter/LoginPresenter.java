package ru.vsu.alexey.surfandroidschool.presenter;

import ru.vsu.alexey.surfandroidschool.contract.LoginContract;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginRequest;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginResponse;




import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.alexey.surfandroidschool.repository.LoginRepository;


public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    private LoginContract.View loginView;
    private LoginContract.Repository loginRepository;


    private String message;

    public LoginPresenter(LoginContract.View view) {
        this.loginView = view;
        this.loginRepository = new LoginRepository();

    }


    @Override
    public void onButtonWasClicked() {
        loginView.showProgress();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setlogin(loginView.getLogin());
        loginRequest.setpassword(loginView.getPassword());

        if (checkValidate(loginRequest)) {

            loginRepository.loginUserRequest(loginRequest)
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();
                            //здесь обработчики кнопок

                            loginRepository.saveUserInfo(loginResponse);

                            loginView.hideProgress();
                            loginView.openMemesScreen();
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            t.printStackTrace();


                            String snackBarMessage = "Во время запроса произошла ошибка, возможно вы неверно ввели логин/пароль";
                            loginView.showSnackbar(snackBarMessage);
                            loginView.hideProgress();
                        }
                    });
        } else {
            loginView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {

    }

    private boolean checkValidate(LoginRequest loginRequest) {
        boolean checkFlag = true;

        if (loginRequest.getlogin().equals("")) {
            loginView.showLoginError("Поле не может быть пустым");
            checkFlag = false;
        }

        if (loginRequest.getpassword().equals("")) {
            loginView.showPasswordError("Поле не может быть пустым");
            checkFlag = false;
        }

        if (!(loginRequest.getlogin().matches("(\\+*)\\d{11}"))) {
            loginView.showLoginError("Логином должен быть номер телефона");
            checkFlag = false;
        }

        if (!(loginRequest.getpassword().matches("\\d{3}"))) {
            loginView.showPasswordError("Пароль должен состоять из 3 цифр");
            checkFlag = false;
        }
        return checkFlag;
    }


}
