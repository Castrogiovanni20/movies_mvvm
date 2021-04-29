package com.aprc.appmvvm.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.aprc.appmvvm.R;
import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.ui.movieDetails.MovieDetailActivity;
import com.aprc.appmvvm.viewmodel.SearchViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements MovieListAdapter.MovieClickListener {
    private TextView textViewNoResult;
    private RecyclerView recyclerView;
    private LottieAnimationView animationLoading;
    private SearchViewModel searchViewModel;
    private MovieListAdapter movieListAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setView();
        setObservers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_home);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search movies here");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void setView(){
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        animationLoading = findViewById(R.id.animationLoading);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        movieListAdapter = new MovieListAdapter(this, movieList, this);
        recyclerView.setAdapter(movieListAdapter);
    }

    private void setObservers() {
        searchViewModel.getMoviesLiveData().observe(this, movies -> {
            if (movies != null){
                movieList = movies;
                movieListAdapter.setMovieList(movieList);
                recyclerView.setVisibility(View.VISIBLE);

                animationLoading.setVisibility(View.INVISIBLE);
            }
        });

        searchViewModel.getLoadingLiveData().observe(this, loading -> {
            if (loading != null && loading) {
                animationLoading.setVisibility(View.VISIBLE);
            }
        });
    }

    private void searchMovies(String query) {
        searchViewModel.getMoviesByQuery(query);
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
