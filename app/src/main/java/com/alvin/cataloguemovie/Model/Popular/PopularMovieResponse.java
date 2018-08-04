package com.alvin.cataloguemovie.Model.Popular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alvin Tandiardi on 31/07/2018.
 */

public class PopularMovieResponse {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("results")
    @Expose
    private List<PopularMovieResult> moviesItem;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }


    public List<PopularMovieResult> getMoviesItem() {
        return moviesItem;
    }

    public void setMoviesItem(List<PopularMovieResult> moviesItem) {
        this.moviesItem = moviesItem;
    }

    public PopularMovieResponse(List<PopularMovieResult> moviesItem) {
        this.moviesItem = moviesItem;
    }
}
