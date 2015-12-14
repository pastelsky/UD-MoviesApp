package com.example.shubhamkanodia.moviesapp.ApiServices;

import com.example.shubhamkanodia.moviesapp.Pojos.MovieResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by shubhamkanodia on 23/11/15.
 */
public interface TheMovieDBService {

    @GET("3/discover/movie")
    Call<MovieResults> getMoviesBy(@Query("sort_by") String sortMethod, @Query("api_key") String apiKey, @Query("vote_count.gte") int minVoteCount);
}
