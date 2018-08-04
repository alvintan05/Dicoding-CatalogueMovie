package com.alvin.cataloguemovie.Retrofit;

import com.alvin.cataloguemovie.API.ApiMovieInterface;
import com.alvin.cataloguemovie.BuildConfig;
import com.alvin.cataloguemovie.Model.Detail.DetailMovie;
import com.alvin.cataloguemovie.Model.Popular.PopularMovieResponse;
import com.alvin.cataloguemovie.Model.Search.SearchMovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alvin Tandiardi on 31/07/2018.
 */

public class ApiClient {

    // Base URL The Movie DB
    private static final String BASE_URL = BuildConfig.BASE_URL;

    private static final String API_KEY = BuildConfig.API_KEY;

    private static ApiClient apiClient;

    private ApiMovieInterface api;

    private ApiClient(ApiMovieInterface api) {
        this.api = api;
    }

    public static ApiClient getInstance() {
        if (apiClient == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiClient = new ApiClient(retrofit.create(ApiMovieInterface.class));
        }
        return apiClient;
    }

    public void getMoviesItem(final OnGetMoviesCallback callback) {
        api.getPopularMovies(API_KEY)
                .enqueue(new Callback<PopularMovieResponse>() {
                    @Override
                    public void onResponse(Call<PopularMovieResponse> call, Response<PopularMovieResponse> response) {
                        if (response.isSuccessful()) {
                            PopularMovieResponse popularMovieResponse = response.body();
                            if (popularMovieResponse != null && popularMovieResponse.getMoviesItem() != null) {
                                callback.onSuccess(popularMovieResponse.getMoviesItem());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<PopularMovieResponse> call, Throwable t) {

                    }
                });
    }

    public void getDetailMovies(int movieId, final OnGetMoviesDetail callback) {

        api.getDetailMovies(movieId, API_KEY)
                .enqueue(new Callback<DetailMovie>() {
                    @Override
                    public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                        if (response.isSuccessful()) {
                            DetailMovie detailMovie = response.body();
                            if (detailMovie != null) {
                                callback.onSuccessDetail(detailMovie);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailMovie> call, Throwable t) {

                    }
                });
    }

    public void getSearchMoviesItem(String language, String querySearch, final OnGetSearchCallback callback) {

        api.getSearchMovies(API_KEY, language, querySearch)
                .enqueue(new Callback<SearchMovieResponse>() {
                    @Override
                    public void onResponse(Call<SearchMovieResponse> call, Response<SearchMovieResponse> response) {
                        SearchMovieResponse searchMovieResponse = response.body();
                        if (response.isSuccessful()) {
                            if (searchMovieResponse != null && searchMovieResponse.getSearchMovieResults() != null) {
                                callback.onSuccess(searchMovieResponse.getSearchMovieResults());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchMovieResponse> call, Throwable t) {

                    }
                });

    }

}
