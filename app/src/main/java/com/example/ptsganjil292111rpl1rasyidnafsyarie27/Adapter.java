package com.example.ptsganjil292111rpl1rasyidnafsyarie27;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MovieViewHolder> {

    public ArrayList<Model> movieModels;
    Context context;

    public Adapter(ArrayList<Model> movieModels, Context context) {
        this.movieModels = movieModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MovieViewHolder holder, int position) {
        holder.tvOriginalTitle.setText(movieModels.get(position).getOriginal_title());
        holder.tvRealeseDate.setText(movieModels.get(position).getRelease_date());
        String url = movieModels.get(position).getPoster_path();
        Glide.with(context)
                .load(url)
                .into(holder.ivPoster);
        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", movieModels.get(position).getId());
                intent.putExtra("original_title", movieModels.get(position).getOriginal_title());
                intent.putExtra("overview", movieModels.get(position).getOverview());
                intent.putExtra("release_date", movieModels.get(position).getRelease_date());
                intent.putExtra("poster_path", movieModels.get(position).getPoster_path());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (movieModels != null) ? movieModels.size() : 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView tvOriginalTitle, tvRealeseDate;
        CardView cvMovie;
        ImageView ivPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOriginalTitle = itemView.findViewById(R.id.tv_original_title);
            tvRealeseDate = itemView.findViewById(R.id.tv_realese_date);
            cvMovie = itemView.findViewById(R.id.cv_movie);
            ivPoster = itemView.findViewById(R.id.iv_poster);

            cvMovie.setOnCreateContextMenuListener(this);
        }


        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
