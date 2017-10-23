package com.example.testvid.View.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.testvid.Presenter.Adapters.RVvideoListAdapter;
import com.example.testvid.Presenter.Interfaces.IPresenterFeautered;
import com.example.testvid.Presenter.PresenterFeautered;
import com.example.testvid.R;
import com.example.testvid.View.Fragments.interfaces.IFragmentFeautered;
import com.example.testvid.View.MainActivity;
import com.example.testvid.Model.pojo.New.Video;

import java.util.List;


public class FragmentFeautered extends Fragment implements IFragmentFeautered{
    private RecyclerView recyclerView;
    private RVvideoListAdapter recyclerAdapter;
    private IPresenterFeautered presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int visibleThreshold = 5;
    private Boolean isLoading = false;
    private int totalItemCount, lastVisibleItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_feautered, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefresh);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvFeautered);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVvideoListAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        presenter = new PresenterFeautered(this);
        presenter.getNewVideos(); //load videos

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        recyclerAdapter = new RVvideoListAdapter(getActivity());
                        recyclerView.setAdapter(recyclerAdapter);
                        presenter.refresh();
                    }
                }
        );


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = recyclerLayoutManager.getItemCount();
                Log.d("Log.d", totalItemCount + "");
                lastVisibleItem = ((LinearLayoutManager) recyclerLayoutManager).findLastVisibleItemPosition();
                Log.d("Log.d", lastVisibleItem + "");
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold )) {
                    isLoading = true;
                    presenter.getNewVideos();
                }
                int i = ((LinearLayoutManager) recyclerLayoutManager).findLastCompletelyVisibleItemPosition();


                Log.d("Log.d", "lastvisibleposition " + i);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void showVideoList(List<Video> videos) {
        swipeRefreshLayout.setRefreshing(false);
        recyclerAdapter.setData(videos);
        isLoading = false;
        try{
            ((MainActivity)getActivity()).setVisibleProgressBar(false);
        } catch (NullPointerException e){}
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
