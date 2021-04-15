package com.aprc.appmvvm.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aprc.appmvvm.R;
import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.ui.movieDetails.MovieDetailActivity;
import com.aprc.appmvvm.viewmodel.SearchViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.MovieClickListener {
    private Button btSearch;
    private EditText etTextSearch;
    private TextView tvNoResult;
    private RecyclerView recyclerView;
    private SearchViewModel searchViewModel;
    private MovieListAdapter movieListAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
    }

    public void setView(){
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        btSearch = findViewById(R.id.btSearch);
        tvNoResult = findViewById(R.id.tvNoResult);
        etTextSearch = findViewById(R.id.etTextSearch);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        movieListAdapter = new MovieListAdapter(this, movieList, this);
        recyclerView.setAdapter(movieListAdapter);

        btSearch.setOnClickListener(v -> searchViewModel.getMoviesByQuery("PEPE"));

        searchViewModel.getMoviesLiveData().observe(this, movies -> {
            if (movies != null){
                movieList = movies;
                movieListAdapter.setMovieList(movieList);
                recyclerView.setVisibility(View.VISIBLE);
                tvNoResult.setVisibility(View.GONE);
            } else {
                tvNoResult.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("plato", movie);
        startActivity(intent);
    }
}
