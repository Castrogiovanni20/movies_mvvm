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
    private TextView textViewOriginalTitle, textViewOverView;
    private ImageView imageViewPosterPath;
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
        setViewModel();
    }

    private void setView() {
        textViewOriginalTitle = findViewById(R.id.TextViewOriginalTitle);
        textViewOverView = findViewById(R.id.TextViewOverView);
        imageViewPosterPath = findViewById(R.id.ImageViewPosterPath);
    }

    private void setViewModel() {
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.getMovieById(movie.getId());
        movieDetailViewModel.getLiveDataMovie().observe(this, movieObj -> {
            if (movieObj != null){
                this.movie = movieObj;
                String URL = movieObj.getPoster_path();
                textViewOriginalTitle.setText(this.movie.getOriginal_title());
                textViewOverView.setText(this.movie.getOverview());

                Glide.with(MovieDetailActivity.this)
                        .load("https://image.tmdb.org/t/p/original/" + URL)
                        .into(imageViewPosterPath);
            }
        });
    }

}
