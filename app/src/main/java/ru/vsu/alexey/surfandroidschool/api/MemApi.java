package ru.vsu.alexey.surfandroidschool.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.vsu.alexey.surfandroidschool.model.MemResponse;

public interface MemApi {
    @GET("memes")
    public Call<List<MemResponse>> postMemes();
}
