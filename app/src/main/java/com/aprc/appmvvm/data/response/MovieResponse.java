package com.aprc.appmvvm.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.aprc.appmvvm.data.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse implements Parcelable {
    @SerializedName("results")
    private List<Movie> movies;

    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    protected MovieResponse(Parcel in) {
        movies = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(movies);
    }
}
