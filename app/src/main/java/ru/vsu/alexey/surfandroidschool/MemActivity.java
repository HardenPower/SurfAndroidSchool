package ru.vsu.alexey.surfandroidschool;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import ru.vsu.alexey.surfandroidschool.FragmentsMainScreen.CreateFragment;
import ru.vsu.alexey.surfandroidschool.FragmentsMainScreen.MemFragment;
import ru.vsu.alexey.surfandroidschool.FragmentsMainScreen.ProfileFragment;
import ru.vsu.alexey.surfandroidschool.contract.MemContract;
import ru.vsu.alexey.surfandroidschool.presenter.MemPresenter;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MemActivity extends AppCompatActivity implements MemContract.View {

    private MemContract.Presenter memesPresenter;
    private final Fragment fragmentMem = new MemFragment();

    private final Fragment fragmentProfile = new ProfileFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    private Fragment active = fragmentMem;
    private Toolbar mActionBarToolbar;
    private BottomNavigationViewEx bottomNavigationViewEx;
    private RecyclerView memesRecyclerView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memes);

        memesPresenter = new MemPresenter(this);

        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#312F51"));
        }
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Популярные мемы");

        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_bar);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setIconSize(30, 30);

        fm.beginTransaction().add(R.id.fragment_container, fragmentProfile, "3").hide(fragmentProfile).commit();

        fm.beginTransaction().add(R.id.fragment_container, fragmentMem, "1").commit();

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return memesPresenter.onButtonNavigationWasClicked(menuItem.getItemId());
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_memes, menu);
        return true;
    }


    @Override
    public void showMemList(){
        fm.beginTransaction().hide(active).show(fragmentMem).commit();
        //getSupportActionBar().setTitle("Популярные мемы");
        active = fragmentMem;
    }

    @Override
    public void showMemCreateActivity(){
        startActivity(new Intent(this, MemesCreateActivity.class));

    }

    @Override
    public void showProfile(){
        fm.beginTransaction().hide(active).show(fragmentProfile).commit();
        //getSupportActionBar().setTitle("Профиль");
        active = fragmentProfile;

    }

}

