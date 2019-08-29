package ru.vsu.alexey.surfandroidschool.presenter;

import ru.vsu.alexey.surfandroidschool.R;
import ru.vsu.alexey.surfandroidschool.contract.MemContract;
import ru.vsu.alexey.surfandroidschool.repository.MemRepository;

public class MemPresenter implements MemContract.Presenter {

    private MemContract.View memesView;
    private MemContract.Repository memesRepository;

    public MemPresenter(MemContract.View view){
        this.memesView = view;
        this.memesRepository = new MemRepository();
    }


    @Override
    public boolean onButtonNavigationWasClicked(int itemId){
        switch (itemId) {
            case R.id.action_list:
                memesView.showMemList();
                return true;
            case R.id.action_create:
                memesView.showMemCreateActivity();
                return false;
            case R.id.action_profile:
                memesView.showProfile();
                return true;
        }


        return false;
    }
    @Override
    public void dataRequest(){

    }
}

