package com.aprc.appmvvm.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.data.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private MovieService movieService;
    private MutableLiveData<List<Movie>> movieListLiveData;
    private MutableLiveData<Movie> movieLiveData;
    private List<Movie> movieList;

    public MovieRepository(){
        this.movieService = RetroInstance.getRetroClient().create(MovieService.class);
        movieListLiveData = new MutableLiveData<>();
        movieLiveData = new MutableLiveData<>();
        movieList = new ArrayList<>();
    }

    public MutableLiveData<List<Movie>> getMovieListLiveData() {
        return movieListLiveData;
    }

    public MutableLiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }

    public void getMoviesByQuery(String query) {
        Call<MovieResponse> call = movieService.getMoviesByQuery("c5288b68f9495429780937c7acd6f748", query);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieList = response.body().getMovies();
                movieListLiveData.postValue(movieList);
                Log.d("ApiResponse", "Respondio la API " + response.code());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                movieListLiveData.postValue(null);
                Log.d("ApiResponse", "Fallo la API" + t.getMessage());
            }
        });
    }

    public void getMoveById(String movieID) {
        Call<Movie> call =  movieService.getMovieById(movieID, "c5288b68f9495429780937c7acd6f748");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieLiveData.postValue(response.body());
                Log.d("ApiResponse", "Respondio la API " + response.code());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                movieLiveData.postValue(null);
                Log.d("ApiResponse", "Fallo la API" + t.getMessage());
            }
        });
    }


}
