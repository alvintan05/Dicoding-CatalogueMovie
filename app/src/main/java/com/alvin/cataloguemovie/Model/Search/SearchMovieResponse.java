package com.alvin.cataloguemovie.Model.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alvin Tandiardi on 04/08/2018.
 */

public class SearchMovieResponse {

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
    private List<SearchMovieResult> searchMovieResults = null;

    public SearchMovieResponse(Integer page, Integer totalResults, Integer totalPages, List<SearchMovieResult> searchMovieResults) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.searchMovieResults = searchMovieResults;
    }

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

    public List<SearchMovieResult> getSearchMovieResults() {
        return searchMovieResults;
    }

    public void setSearchMovieResults(List<SearchMovieResult> searchMovieResults) {
        this.searchMovieResults = searchMovieResults;
    }
}
