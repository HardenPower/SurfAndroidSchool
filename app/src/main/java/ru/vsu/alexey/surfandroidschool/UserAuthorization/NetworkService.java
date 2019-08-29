package ru.vsu.alexey.surfandroidschool.UserAuthorization;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.alexey.surfandroidschool.api.MemApi;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://demo3161256.mockable.io/";
    private Retrofit retrofit;


    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }


    public AuthorizationInterface getAuthorizationInterface(){

        return retrofit.create(AuthorizationInterface.class);
    }
    public MemApi getMemesApiInterface(){
        return retrofit.create(MemApi.class);
    }


}
