package id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.ComingSoonFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.NowPlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.TeaterFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.model.Results;

/**
 * Created by Andini Novidayanti A on 13/05/2017.
 */

public class NowPlaying extends RecyclerView.Adapter<NowPlaying.ViewHolder> {

    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Results> movieList;
    NowPlayingFragment nowPlayingFragment;
    TeaterFragment teaterFragment;
    ComingSoonFragment comingSoonFragment;
    Context context;
    private int lastposition = -1;

    public NowPlaying(NowPlayingFragment nowPlayingFragment, ArrayList<Results> movieList, Context context) {
        this.movieList = movieList;
        this.nowPlayingFragment = nowPlayingFragment;
        this.context = context;
    }

    public NowPlaying(TeaterFragment teaterFragment, ArrayList<Results> mlist, Context context) {
        this.movieList = mlist;
        this.teaterFragment = teaterFragment;
        this.context = context;
    }

    public NowPlaying(ComingSoonFragment comingSoonFragment, ArrayList<Results> mlist, Context context) {
        this.movieList = mlist;
        this.comingSoonFragment = comingSoonFragment;
        this.context = context;
    }

    @Override
    public NowPlaying.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NowPlaying.ViewHolder holder, int position) {
        Results results  = movieList.get(position);
        holder.tvJudul.setText(results.judul);
        holder.tvDeskripsi.setText(results.overview);
        image = url+results.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageViewFoto);
    }

    @Override
    public int getItemCount() {
        if (movieList != null)
            return movieList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewFoto;
        TextView tvJudul;
        TextView tvDeskripsi;


        public ViewHolder(View itemView) {
            super(itemView);
            imageViewFoto = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewName);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDesc);
        }
    }
}
