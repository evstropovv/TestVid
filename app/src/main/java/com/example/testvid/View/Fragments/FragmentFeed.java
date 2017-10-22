package com.example.testvid.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.testvid.Presenter.Adapters.RVvideoListAdapter;
import com.example.testvid.Presenter.Interfaces.IPresenterFeed;
import com.example.testvid.Presenter.PresenterFeed;
import com.example.testvid.R;
import com.example.testvid.View.Fragments.interfaces.IFragmentFeed;
import com.example.testvid.View.MainActivity;
import com.example.testvid.Model.pojo.New.Video;

import java.util.List;

public class FragmentFeed extends Fragment implements IFragmentFeed {
    private RecyclerView recyclerView;
    private RVvideoListAdapter recyclerAdapter;
    private IPresenterFeed presenter;

    private int visibleThreshold = 2;
    private Boolean isLoading = false;
    private int totalItemCount, lastVisibleItem;


    public FragmentFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvFeed);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVvideoListAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        presenter = new PresenterFeed(this);
        presenter.getNewVideos(); //load videos
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = recyclerLayoutManager.getItemCount();
                Log.d("Log.d", totalItemCount + "");
                lastVisibleItem = ((LinearLayoutManager) recyclerLayoutManager).findLastVisibleItemPosition();
                Log.d("Log.d", lastVisibleItem + "");
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    presenter.getNewVideos();
                    isLoading = true;
                }
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
        recyclerAdapter.setData(videos);
        isLoading = false;
        try{
            ((MainActivity)getActivity()).setVisibleProgressBar(false);
        } catch (NullPointerException e){}
    }

    @Override
    public void showError(String msg) {

    }
}
