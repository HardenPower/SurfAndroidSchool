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
import ru.vsu.alexey.surfandroidschool.contract.LoginContract;
import ru.vsu.alexey.surfandroidschool.presenter.LoginPresenter;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.support.design.widget.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    //определяем поля и кнопку с помощи библиотеки TextFieldBoxes

    private TextFieldBoxes loginFieldBoxes;
    private ExtendedEditText loginFieldText;

    private TextFieldBoxes passwordFieldBoxes;
    private ExtendedEditText passwordFieldText;

    private Button loginButton;
    private ProgressBar progressBar;

    private String errorMessage = "";
    private LoginContract.Presenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().hide();
        loginPresenter = new LoginPresenter(this);

        showView();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onButtonWasClicked();
            }
        });

    }

    private void showView() {
        loginFieldBoxes = findViewById(R.id.loginFieldBoxes);
        loginFieldText = findViewById(R.id.login_ed_text);

        passwordFieldBoxes = findViewById(R.id.passwordFieldBoxes);
        passwordFieldText = findViewById(R.id.pass_ed_text);

        loginButton = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }


    @Override
    public void showError() {

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        loginButton.setText(R.string.login);
    }

    @Override
    public void showProgress() {
        loginButton.setText("");
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void showSnackbar(String message) {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#FF575D"));
        snackbar.show();
    }

    @Override
    public void showLoginError(String message) {
        loginFieldBoxes.setError(message, false);
    }

    @Override
    public void showPasswordError(String message) {
        passwordFieldBoxes.setError(message, false);
    }

    @Override
    public String getLogin() {
        return loginFieldText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordFieldText.getText().toString();
    }

    @Override
    public void openMemesScreen() {
        Intent i = new Intent(LoginActivity.this,
                MemActivity.class);
        startActivity(i);
        finish();
    }
}
