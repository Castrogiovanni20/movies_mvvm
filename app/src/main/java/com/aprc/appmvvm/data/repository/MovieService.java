package com.aprc.appmvvm.data.repository;

import com.aprc.appmvvm.data.model.Movie;
import com.aprc.appmvvm.data.response.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("search/movie")
    Call<MovieResponse> getMoviesByQuery(@Query("api_key") String api_key,
                                         @Query("query") String query);

    @GET("photos/{albumId}")
    Call<Movie> getMovieById(@Path("albumId") String albumId);
}
