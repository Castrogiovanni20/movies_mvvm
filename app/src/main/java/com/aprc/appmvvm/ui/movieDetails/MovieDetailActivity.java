package com.aprc.appmvvm.ui.movieDetails;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.aprc.appmvvm.R;
import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.viewmodel.MovieDetailViewModel;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView tvOriginalTitle, tvOverview;
    private ImageView ivPoster_path;
    private Movie movie;
    private MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent().getExtras() != null) {
           movie = getIntent().getExtras().getParcelable("movie");
        }

        setView();
    }

    private void setView(){
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        tvOriginalTitle = findViewById(R.id.tvOriginal_title);
        tvOverview = findViewById(R.id.tvOverview);
        ivPoster_path = findViewById(R.id.ivPoster_path);

        movieDetailViewModel.getMovieById(movie.getId());
        movieDetailViewModel.getLiveDataPlato().observe(this, platoObj -> {
            if (platoObj != null){
                this.movie = platoObj;
                String URL = platoObj.getPoster_path();
                tvOriginalTitle.setText(this.movie.getOriginal_title());
                tvOverview.setText(this.movie.getOverview());

                Glide.with(MovieDetailActivity.this)
                        .load("https://image.tmdb.org/t/p/original/" + URL)
                        .into(ivPoster_path);
            }
        });
    }

}
