package com.alvin.cataloguemovie.Entity.Movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alvin Tandiardi on 31/07/2018.
 */

public class MovieResult {

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;

    @SerializedName("id")
    @Expose
    private int movieId;

    @SerializedName("video")
    @Expose
    private Boolean video;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    @SerializedName("title")
    @Expose
    private String movieTitle;

    @SerializedName("poster_path")
    @Expose
    private String moviePosterPath;

    @SerializedName("release_date")
    @Expose
    private String movieReleaseDate;

    @SerializedName("overview")
    @Expose
    private String movieDescription;

    @SerializedName("popularity")
    @Expose
    private Double popularity;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("adult")
    @Expose
    private Boolean adult;

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}