package ru.vsu.alexey.surfandroidschool.repository;


import ru.vsu.alexey.surfandroidschool.contract.MemContract;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.NetworkService;


import java.util.List;
import retrofit2.Call;
import ru.vsu.alexey.surfandroidschool.model.MemResponse;

public class MemRepository implements MemContract.Repository {
    @Override
    public Call<List<MemResponse>> memesListRequest(){
        return NetworkService.getInstance().getMemesApiInterface().postMemes();
    }

}


