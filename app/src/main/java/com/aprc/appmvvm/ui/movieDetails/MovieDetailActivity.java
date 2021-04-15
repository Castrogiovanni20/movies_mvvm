package com.aprc.appmvvm.ui.movieDetails;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.aprc.appmvvm.R;
import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.viewmodel.MovieDetailViewModel;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvAlbumId, tvThumbnailUrl;
    private Movie movie;
    private MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent().getExtras() != null) {
           movie = getIntent().getExtras().getParcelable("plato");
        }

        setView();
    }

    private void setView(){
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        tvTitle = findViewById(R.id.tvTitle);
        tvAlbumId = findViewById(R.id.tvAlbumId);
        tvThumbnailUrl = findViewById(R.id.tvThumbnailUrl);

       // movieDetailViewModel.getDataPlatoByID();
        movieDetailViewModel.getLiveDataPlato().observe(this, platoObj -> {
            if (platoObj != null){
                this.movie = platoObj;

            }
        });
    }

}
