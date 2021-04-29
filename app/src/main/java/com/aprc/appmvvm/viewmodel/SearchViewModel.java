package com.aprc.appmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.data.repository.MovieRepository;
import com.aprc.appmvvm.data.repository.MovieService;
import com.aprc.appmvvm.data.repository.RetroInstance;
import com.aprc.appmvvm.data.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<List<Movie>> movieListLiveData;
    private MutableLiveData<Boolean> loadingLiveData;

    public SearchViewModel() {
        movieRepository = new MovieRepository();
        movieListLiveData = movieRepository.getMovieListLiveData();
        loadingLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<Movie>> getMoviesLiveData(){
        return movieListLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void getMoviesByQuery(String query){
        loadingLiveData.setValue(true);
        movieRepository.getMoviesByQuery(query);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
