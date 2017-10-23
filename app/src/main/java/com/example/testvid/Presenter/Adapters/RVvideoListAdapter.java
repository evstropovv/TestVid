package com.example.testvid.Presenter.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testvid.R;
import com.example.testvid.View.VideoPlayer;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RVvideoListAdapter extends RecyclerView.Adapter<RVvideoListAdapter.ViewHolder>{
    private List<Video> videos;
    private Context ctx;
    private SimpleExoPlayer player;
    private int visiblePosition;

    public RVvideoListAdapter(Context context) {
        this.ctx = context;
        videos = new ArrayList<>();
    }

    public void setData(List<Video> list) {
        if (list != null && list.size() > 0) {
            this.videos.clear();
            this.videos.addAll(list);
            notifyDataSetChanged();
            Log.d("Log.d", "video list size " + videos.size());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(videos.get(position).getTitle());
        int likes = videos.get(position).getLikesCount();
        holder.tvVotes.setText(likes == 1 ? likes + " like" : likes + " likes");
        try {
            Picasso.with(ctx).load(videos.get(position).getThumbnailUrl()).into(holder.ivImage);
        } catch (Error e) {
            Log.d("Log.d", "error Picasso " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvVotes;
        public ImageView ivImage;
        public SimpleExoPlayerView videoView;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivImage = (ImageView) itemView.findViewById(R.id.ivPhoto);
            tvVotes = (TextView) itemView.findViewById(R.id.tvVotes);
            videoView = (SimpleExoPlayerView) itemView.findViewById(R.id.videoView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, VideoPlayer.class);
                    intent.putExtra("url", videos.get(getAdapterPosition()).getCompleteUrl());
                    ctx.startActivity(intent);
                }
            });

        }
    }
}
