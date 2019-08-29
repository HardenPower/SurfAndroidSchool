package ru.vsu.alexey.surfandroidschool.contract;

import android.view.Menu;

import java.util.List;

import retrofit2.Call;
import ru.vsu.alexey.surfandroidschool.model.MemResponse;
public interface MemContract {

    interface View {
        void showMemList();
        void showMemCreateActivity();
        void showProfile();
    }

    interface Presenter {
        boolean onButtonNavigationWasClicked(int itemId);
        void dataRequest();
    }

    interface Repository {
        Call<List<MemResponse>> memesListRequest();

    }


}