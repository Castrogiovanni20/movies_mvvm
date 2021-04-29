package com.aprc.appmvvm.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprc.appmvvm.R;
import com.aprc.appmvvm.data.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.movieListHolder> {
    private Context context;
    private List<Movie> movieList;
    private MovieClickListener movieClickListener;

    public MovieListAdapter(Context context, List<Movie> movieList, MovieClickListener movieClickListener){
        this.context = context;
        this.movieList = movieList;
        this.movieClickListener = movieClickListener;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public movieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_movie, parent, false);
        return new movieListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieListHolder holder, int position) {
        String URL = this.movieList.get(position).getPoster_path();
        holder.textViewTitle.setText(this.movieList.get(position).getOriginal_title());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/" + URL)
                .into(holder.imageViewThumbnail);
        //Picasso.get().load(URL).into(holder.ivThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieClickListener.onMovieClick(movieList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.movieList != null){
            return this.movieList.size();
        }
        return 0;
    }

    public class movieListHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewThumbnail;
        private TextView textViewTitle;

        public movieListHolder(View itemView) {
            super(itemView);
            imageViewThumbnail = itemView.findViewById(R.id.ImageViewThumbnail);
            textViewTitle = itemView.findViewById(R.id.TextViewTitle);

        }
    }

    public interface MovieClickListener {
        void onMovieClick(Movie movie);
    }
}
