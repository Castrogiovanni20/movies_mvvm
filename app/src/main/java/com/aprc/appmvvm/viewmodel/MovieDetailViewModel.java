package com.aprc.appmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
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

public class MovieDetailViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<Movie> movieLiveData;
    private MutableLiveData<Boolean> loadingLiveData;

    public MovieDetailViewModel() {
        movieRepository = new MovieRepository();
        movieLiveData = movieRepository.getMovieLiveData();
        loadingLiveData = new MutableLiveData<>();
    }

    public LiveData<Movie> getLiveDataMovie() {
        return movieLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void getMovieById(String movieID){
        movieRepository.getMoveById(movieID);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
