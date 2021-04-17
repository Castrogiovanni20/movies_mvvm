package com.aprc.appmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.data.repository.MovieService;
import com.aprc.appmvvm.data.repository.RetroInstance;
import com.aprc.appmvvm.data.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailViewModel extends ViewModel {
    private MovieService movieService;
    private MutableLiveData<Movie> moviesLiveData;

    public MovieDetailViewModel() {
        this.movieService = RetroInstance.getRetroClient().create(MovieService.class);
        moviesLiveData = new MutableLiveData<>();
    }

    public LiveData<Movie> getLiveDataPlato() {
        return moviesLiveData;
    }

    public void getMovieById(String movieID){
        Call<Movie> call =  movieService.getMovieById(movieID, "c5288b68f9495429780937c7acd6f748");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                moviesLiveData.postValue(response.body());
                Log.d("API", "Respondio la API " + response.code());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                moviesLiveData.postValue(null);
                Log.d("API", "Fallo la API" + t.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
