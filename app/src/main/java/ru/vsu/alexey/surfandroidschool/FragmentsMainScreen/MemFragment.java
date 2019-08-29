package ru.vsu.alexey.surfandroidschool.FragmentsMainScreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.alexey.surfandroidschool.MemesDetailActivity;
import ru.vsu.alexey.surfandroidschool.R;
import ru.vsu.alexey.surfandroidschool.RecyclerViewAdapter;
import ru.vsu.alexey.surfandroidschool.UserAuthorization.NetworkService;
import ru.vsu.alexey.surfandroidschool.model.MemResponse;

public class MemFragment extends Fragment implements RecyclerViewAdapter.OnItemListener {
    View view;
    private static final String TAG = "MemesPresenter";

    private RecyclerView recyclerView;
    private List<MemResponse> memesList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout refreshLayout;
    private TextView textPart1;
    private TextView textPart2;

    private Snackbar snackbar;



    public MemFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_memes, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.memes_recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), memesList);

        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerViewAdapter.setClickListener(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        textPart1 = (TextView) view.findViewById(R.id.text_part_1);
        textPart2 = (TextView) view.findViewById(R.id.text_part_2);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener (refreshListener);

        refreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorAccent));


        textPart1.setVisibility(View.INVISIBLE);
        textPart2.setVisibility(View.INVISIBLE);

        snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Произошла ошибка", Snackbar.LENGTH_SHORT);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        memesList = new ArrayList<>();
        setData();


    }


    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

        @Override
        public void onRefresh() {
            final int SPLASH_DELAY = 800;
            refreshLayout.setRefreshing(true);
            setData();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    refreshLayout.setRefreshing(false);
                }
            }, SPLASH_DELAY);
        }
    };
    public void setData(/*List<MemesResponse> memList*/) {
        memesList.clear();
        NetworkService
                .getInstance()
                .getMemesApiInterface()
                .postMemes()
                .enqueue(new Callback<List<MemResponse>>() {
                    @Override
                    public void onResponse(Call<List<MemResponse>> call, Response<List<MemResponse>> response) {
                        textPart1.setVisibility(View.INVISIBLE);
                        textPart2.setVisibility(View.INVISIBLE);
                        List<MemResponse> memesResponse = response.body();
                        memesList.addAll(response.body());
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MemResponse>> call, Throwable t) {
                        t.printStackTrace();

                        setData();

                        String snackBarMessage = "Во время запроса произошла ошибка, возможно вы неверно ввели логин/пароль";

                        textPart1.setVisibility(View.VISIBLE);
                        textPart2.setVisibility(View.VISIBLE);
                        //hideProgress();

                        View sbView = snackbar.getView();
                        sbView.setBackgroundColor(Color.parseColor("#FF575D"));
                        snackbar.show();
                        recyclerView.getAdapter().notifyDataSetChanged();

                    }
                });
    }
    @Override
    public void OnItemClick(View view, int position) {

        startActivity(new Intent(getActivity(), MemesDetailActivity.class));
        Log.d(TAG, "onItemClick: "+position);
    }
    public void hideProgress() {

    }
    public void showProgress() {

    }
}

