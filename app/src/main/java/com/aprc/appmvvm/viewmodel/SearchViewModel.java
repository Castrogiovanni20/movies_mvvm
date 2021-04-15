package com.aprc.appmvvm.viewmodel;

import android.util.Log;

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

public class SearchViewModel extends ViewModel {
    private MovieService movieService;
    private MutableLiveData<List<Movie>> movieListLiveData;
    private List<Movie> movieList; // No se esta usando, por que no agregamos nada a la lista todavia

    public SearchViewModel() {
        movieService = RetroInstance.getRetroClient().create(MovieService.class);
        movieListLiveData = new MutableLiveData<>();
        movieList = new ArrayList<>();
    }

    public MutableLiveData<List<Movie>> getMoviesLiveData(){
        return movieListLiveData;
    }

    public void getMoviesByQuery(String query){
        Call<MovieResponse> call = movieService.getMoviesByQuery("c5288b68f9495429780937c7acd6f748", query);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieList = response.body().getMovies();
                movieListLiveData.postValue(movieList);
                Log.d("API", "Respondio la API " + response.code());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                movieListLiveData.postValue(null);
                Log.d("API", "Fallo la API" + t.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
