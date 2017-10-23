package com.example.testvid.View.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.testvid.Presenter.Adapters.RVvideoListAdapter;
import com.example.testvid.Presenter.Interfaces.IPresenterFeed;
import com.example.testvid.Presenter.PresenterFeed;
import com.example.testvid.R;
import com.example.testvid.View.Fragments.interfaces.IFragmentFeed;
import com.example.testvid.View.MainActivity;
import com.example.testvid.Model.pojo.New.Video;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class FragmentFeed extends Fragment implements IFragmentFeed {
    private RecyclerView recyclerView;
    private RVvideoListAdapter recyclerAdapter;
    private IPresenterFeed presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int visibleThreshold = 5;
    private Boolean isLoading = false;
    private int totalItemCount, lastVisibleItem;
    private List<Video> videos;
    private View currentFocusedLayout, oldFocusedLayout;
    private SimpleExoPlayer player;
    private int lastPositionView;

    public FragmentFeed() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) player.stop();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        videos = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvFeed);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVvideoListAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);

        presenter = new PresenterFeed(this);
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
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    //get the recyclerview position which is completely visible and first
                    int positionView = ((LinearLayoutManager) recyclerLayoutManager).findFirstCompletelyVisibleItemPosition();
                    if (positionView != lastPositionView) {
                        lastPositionView = positionView;
                        Log.d("VISISBLE POSITION", positionView + "");
                        currentFocusedLayout = ((LinearLayoutManager) recyclerLayoutManager).findViewByPosition(positionView);
                        if (currentFocusedLayout != null) {
                            SimpleExoPlayerView videoViewCurrent = (SimpleExoPlayerView) currentFocusedLayout.findViewById(R.id.videoView);
                            ImageView imageView = (ImageView) currentFocusedLayout.findViewById(R.id.ivPhoto);
                            videoViewCurrent.setPlayer(player);

                            if (positionView >= 0) {
                                if (oldFocusedLayout != null) {
                                    //Stop the previous video playback after new scroll
                                    ImageView imageViewOld = (ImageView) oldFocusedLayout.findViewById(R.id.ivPhoto);
                                    SimpleExoPlayerView videoViewOld = (SimpleExoPlayerView) oldFocusedLayout.findViewById(R.id.videoView);
                                    videoViewOld.setVisibility(View.INVISIBLE);
                                    imageViewOld.setVisibility(View.VISIBLE);
                                    player.stop();
                                }

                                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "Vidme"));
                                HlsDataSourceFactory hlsDataSourceFactory = new DefaultHlsDataSourceFactory(dataSourceFactory);


                                MediaSource videoSource = new HlsMediaSource(Uri.parse(videos.get(positionView).getCompleteUrl()),
                                        hlsDataSourceFactory, 1, null, null);

                                //to play video of selected recylerview;
                                videoViewCurrent.setVisibility(View.VISIBLE);
                                player.prepare(videoSource);
                                player.setPlayWhenReady(true);
                                imageView.setVisibility(View.INVISIBLE);
                                oldFocusedLayout = currentFocusedLayout;
                            }
                        }
                    }
                }
            }

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
    public void onPause() {
        super.onPause();
        player.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void showVideoList(List<Video> videos) {
        this.videos.clear();
        this.videos.addAll(videos);
        swipeRefreshLayout.setRefreshing(false);
        recyclerAdapter.setData(videos);
        isLoading = false;
        try {
            ((MainActivity) getActivity()).setVisibleProgressBar(false);
        } catch (NullPointerException e) {
        }
    }

    @Override
    public void showError(String msg) {

    }

}
