package ru.vsu.alexey.surfandroidschool;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
//import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import ru.vsu.alexey.surfandroidschool.SharedPreferencesUtil.SharedPreferencesUtil;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginRequest;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.LoginResponse;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.NetworkService;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.support.design.widget.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends AppCompatActivity {

    //определяем поля и кнопку с помощи библиотеки TextFieldBoxes

    TextFieldBoxes loginFieldBoxes;
    ExtendedEditText loginFieldText;

    TextFieldBoxes passwordFieldBoxes;
    ExtendedEditText passwordFieldText;

    Button loginButton;
    ProgressBar progressBar;

    String errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        loginFieldBoxes = findViewById(R.id.loginFieldBoxes);
        loginFieldText = findViewById(R.id.login_ed_text);

        passwordFieldBoxes = findViewById(R.id.passwordFieldBoxes);
        passwordFieldText = findViewById(R.id.pass_ed_text);

        loginButton = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }


    public void onClick(View view) {
        loginButton.setText("error");
        progressBar.setVisibility(ProgressBar.VISIBLE);
        final String login = loginFieldText.getText().toString();
        String password = passwordFieldText.getText().toString();

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setlogin(login);
        loginRequest.setpassword(password);

        Boolean checkValidate = true;
        if (!ValidateFields(login, true)) {
            loginFieldBoxes.setError(errorMessage, false);
            checkValidate = false;
        }
        if (!ValidateFields(password, false)) {
            passwordFieldBoxes.setError(errorMessage, false);
            checkValidate = false;
        }

        final Snackbar snackbar = Snackbar.make(view, "Во время запроса произошла ошибка, возможно вы неверно ввели логин/пароль", Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#FF575D"));

        if (checkValidate) {
            NetworkService.getInstance()
                    .getAuthorizationInterface()
                    .postData(loginRequest)
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();

                            SharedPreferencesUtil.putString(SharedPreferencesUtil.TOKEN, loginResponse.getAccessToken());
                            SharedPreferencesUtil.putInt(SharedPreferencesUtil.ID, loginResponse.getId());
                            SharedPreferencesUtil.putString(SharedPreferencesUtil.USERNAME, loginResponse.getUsername());
                            SharedPreferencesUtil.putString(SharedPreferencesUtil.FIRSTNAME, loginResponse.getFirstName());
                            SharedPreferencesUtil.putString(SharedPreferencesUtil.LASTNAME, loginResponse.getLastName());
                            SharedPreferencesUtil.putString(SharedPreferencesUtil.USER_DESCRIPTION, loginResponse.getUserDescription());

                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            loginButton.setText("Войти");

                            Intent intent = new Intent(LoginActivity.this,
                                    MemesActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            t.printStackTrace();

                            snackbar.show();
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            loginButton.setText("Войти");
                        }
                    });
        } else {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            loginButton.setText("Войти");
        }

/*        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                loginButton.setText("Войти");
                if(!ValidateFields(loginFieldText.getText().toString(), true))
                    loginFieldBoxes.setError(errorMessage, false);
                if(!ValidateFields(passwordFieldText.getText().toString(), false))
                passwordFieldBoxes.setError(errorMessage, false);

            }
        }, 1000); */
    }

    private boolean ValidateFields(String textEdit, boolean isLogin) {
        if (textEdit.equals("")) {
            errorMessage = "Поле не может быть пустым";
            return false;
        }
        if (isLogin && !textEdit.matches("(\\+*)\\d{11}")) {
            errorMessage = "Логином должен быть номер телефона";
            return false;
        }

        if (!isLogin && !textEdit.matches("\\d{3}")) {
            errorMessage = "Пароль должен состоять из 3 цифр";
            return false;

        }

        return true;
    }
}
