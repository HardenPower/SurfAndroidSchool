package ru.vsu.alexey.surfandroidschool;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;


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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                loginButton.setText("Войти");
                if(!ValidateFields(loginFieldText.getText().toString(), true))
                    loginFieldBoxes.setError(errorMessage, false);
                if(!ValidateFields(passwordFieldText.getText().toString(), false))
                passwordFieldBoxes.setError(errorMessage, false);

            }
        }, 1000);
    }
    private boolean ValidateFields(String textEdit, boolean isLogin) {
        if (textEdit.equals("")) {
            errorMessage = "Поле не может быть пустым";
            return false;
        }
        if (isLogin && !textEdit.matches("(\\\\+*)\\\\d{11}\"")) {
            errorMessage = "Логином должен быть номер телефона";
            return false;
        }

        if (isLogin && !textEdit.matches("\\d{3}")) {
            errorMessage = "Пароль должен состоять из 3 цифр";
            return false;

        }

        return true;
    }

}
