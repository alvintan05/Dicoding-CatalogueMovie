package com.alvin.cataloguemovie.Retrofit;

import com.alvin.cataloguemovie.Model.Detail.DetailMovie;

/**
 * Created by Alvin Tandiardi on 02/08/2018.
 */

public interface OnGetMoviesDetail {

    void onSuccessDetail(DetailMovie detailMovies);

    void onError();

}
