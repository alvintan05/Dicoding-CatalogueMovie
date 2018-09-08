package com.alvin.cataloguemovie.API;

import com.alvin.cataloguemovie.Entity.Detail.DetailMovie;
import com.alvin.cataloguemovie.Entity.Movies.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alvin Tandiardi on 31/07/2018.
 */

public interface ApiMovieInterface {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page, @Query("region") String region);

    @GET("movie/{movie_id}")
    Call<DetailMovie> getDetailMovies(@Path("movie_id") int movie_id, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> getSearchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String querySearch);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page, @Query("region") String region);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page, @Query("region") String region);
}
