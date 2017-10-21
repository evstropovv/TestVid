package com.example.testvid.View.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testvid.Presenter.Adapters.RVnewAdapter;
import com.example.testvid.Presenter.Interfaces.IPresenterNew;
import com.example.testvid.Presenter.PresenterNew;
import com.example.testvid.R;
import com.example.testvid.View.Fragments.interfaces.IFragmentNew;
import com.example.testvid.pojo.New.Video;

import java.util.List;


public class FragmentNew extends Fragment implements IFragmentNew {
    private RecyclerView recyclerView;
    private RVnewAdapter recyclerAdapter;
    private IPresenterNew presenter;

    private int visibleThreshold = 3;
    private Boolean isLoading = false;
    private int totalItemCount, lastVisibleItem;

    public FragmentNew() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_new, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvNew);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVnewAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        presenter = new PresenterNew(this);
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
    public void showVideoList(List<Video> videos) {
        recyclerAdapter.setData(videos);
        isLoading = false;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
