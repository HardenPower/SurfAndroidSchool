package ru.vsu.alexey.surfandroidschool;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

//import ru.vsu.alexey.surfandroidschool.SharedPreferencesUtil.SharedPreferencesUtil;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DELAY = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
       // SharedPreferencesUtil.init(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openLoginScreen();
                finish();
            }
        }, SPLASH_DELAY);

        }
    private void openLoginScreen() {
        Intent i = new Intent(SplashActivity.this,
                MemActivity.class);
        startActivity(i);
        finish();
    }
}


