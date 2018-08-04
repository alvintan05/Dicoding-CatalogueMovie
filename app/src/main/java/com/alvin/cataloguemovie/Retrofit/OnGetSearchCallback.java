package com.alvin.cataloguemovie.Retrofit;

import com.alvin.cataloguemovie.Model.Search.SearchMovieResult;

import java.util.List;

/**
 * Created by Alvin Tandiardi on 04/08/2018.
 */

public interface OnGetSearchCallback {

    void onSuccess(List<SearchMovieResult> searchMoviesItem);

    void onError();

}
