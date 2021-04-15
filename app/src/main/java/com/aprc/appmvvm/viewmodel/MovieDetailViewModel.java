package com.aprc.appmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.data.repository.MovieService;
import com.aprc.appmvvm.data.repository.RetroInstance;

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

    /*
    public void getDataPlatoByID(String query){
        //Call<Movie> call =  movieService.getMoviesByQuery("c5288b68f9495429780937c7acd6f748", query);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                moviesLiveData.postValue(response.body());
                Log.d("API", "Respondio la API" + response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                platoLiveData.postValue(null);
                Log.d("API", "Fallo la API" + t.getMessage());
            }
        });
    }*/

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
