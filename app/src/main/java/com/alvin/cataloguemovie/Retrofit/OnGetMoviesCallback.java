package com.alvin.cataloguemovie.Retrofit;

import com.alvin.cataloguemovie.Model.Popular.PopularMovieResult;

import java.util.List;

/**
 * Created by Alvin Tandiardi on 01/08/2018.
 */

public interface OnGetMoviesCallback {

    void onSuccess(List<PopularMovieResult> moviesItem);

    void onError();

}
