package com.example.testvid.Presenter.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.testvid.R;
import com.example.testvid.pojo.New.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RVnewAdapter extends RecyclerView.Adapter<RVnewAdapter.ViewHolder> {
    private List<Video>  videos;
    private Context ctx;

    public RVnewAdapter(Context context){
        this.ctx = context;
        videos = new ArrayList<>();
    }

    public void setData(List<Video> list){
        if (list!=null && list.size()>0){
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
        holder.tvVotes.setText(videos.get(position).getLikesCount()+"");
        try{
            Picasso.with(ctx).load(videos.get(position).getThumbnailUrl()).into(holder.ivImage);
        } catch (Error e){
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
        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView)itemView.findViewById(R.id.tvName);
            ivImage = (ImageView)itemView.findViewById(R.id.ivPhoto);
            tvVotes = (TextView)itemView.findViewById(R.id.tvVotes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
