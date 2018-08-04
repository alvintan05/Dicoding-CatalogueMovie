package com.alvin.cataloguemovie;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvin.cataloguemovie.Retrofit.ApiClient;
import com.alvin.cataloguemovie.Retrofit.OnGetMoviesDetail;
import com.alvin.cataloguemovie.Model.Detail.DetailMovie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMoviesActivity extends AppCompatActivity {

    private static final String TAG = "DetailMoviesActivity";

    private int mutedColor = R.attr.colorPrimary;

    public static String MOVIE_ID = "movie_id";

    private int movie_id;

    private static final String IMAGE_BASE_URL = BuildConfig.IMAGE_BASE_URL;

    private ApiClient apiClient;

    @BindView(R.id.tb)
    Toolbar tb;

    @BindView(R.id.collapseit)
    CollapsingToolbarLayout ctl;

    @BindView(R.id.img_poster)
    ImageView imgPoster;

    @BindView(R.id.img_backdrop)
    ImageView imgBackdrop;

    @BindView(R.id.detail_movie_title_1)
    TextView movieTitleBig;

    @BindView(R.id.detail_movie_year)
    TextView movieYear;

    @BindView(R.id.detail_movie_tagline)
    TextView movieTagline;

    @BindView(R.id.detail_movie_rate)
    TextView movieRate;

    @BindView(R.id.detail_movie_status)
    TextView movieReleaseStatus;

    @BindView(R.id.detail_movie_date)
    TextView movieReleaseDate;

    @BindView(R.id.detail_movie_language)
    TextView movieLanguage;

    @BindView(R.id.detail_movie_runtime)
    TextView movieRuntime;

    @BindView(R.id.detail_movie_overview)
    TextView movieOverview;

    @BindView(R.id.detail_movie_homepage)
    TextView movieHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);
        ButterKnife.bind(this);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ctl.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        movie_id = getIntent().getIntExtra(MOVIE_ID, movie_id);

        Log.d(TAG, "onCreate: " + movie_id);

        apiClient = ApiClient.getInstance();

        getDetailMovie();

    }

    private void getDetailMovie() {
        apiClient.getDetailMovies(movie_id, new OnGetMoviesDetail() {
            @Override
            public void onSuccessDetail(DetailMovie detailMovies) {

                String poster_url = IMAGE_BASE_URL + "w342" + detailMovies.getPosterPath();
                String backdrop_url = IMAGE_BASE_URL + "w500" + detailMovies.getBackdropPath();

                movieTitleBig.setText(detailMovies.getTitle());
                movieYear.setText(detailMovies.getReleaseDate().split("-")[0]);

                //buat set apabila taglinenya kosong
                if (!detailMovies.getTagline().isEmpty()) {
                    movieTagline.setText("\"" + detailMovies.getTagline() + "\"");
                } else {
                    movieTagline.setText(" ");
                }

                movieRate.setText(detailMovies.getVoteAverage().toString());
                movieReleaseStatus.setText(detailMovies.getStatus());
                movieReleaseDate.setText(detailMovies.getReleaseDate());
                movieRuntime.setText(detailMovies.getRuntime() + " mins");
                movieOverview.setText(detailMovies.getOverview());
                movieHomepage.setText(detailMovies.getHomepage());

                Glide.with(getApplicationContext())
                        .load(poster_url)
                        .placeholder(R.drawable.example)
                        .error(R.drawable.example)
                        .crossFade()
                        .into(imgPoster);

                Glide.with(getApplicationContext())
                        .load(backdrop_url)
                        .placeholder(R.drawable.example_backdrop)
                        .error(R.drawable.example_backdrop)
                        .crossFade()
                        .into(imgBackdrop);

                //set title toolbar sesuai judul
                ctl.setTitle(detailMovies.getTitle());

                // mengubah gambar poster menjadi bitmap
                int myWidth = 600;
                int myHeight = 900;

                Glide.with(getApplicationContext())
                        .load(poster_url)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(myWidth, myHeight) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                // mengekstrak warna dari gambar yang digunakan
                                Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(Palette palette) {
                                        mutedColor = palette.getDarkVibrantColor(R.attr.colorPrimary);
                                        ctl.setContentScrimColor(mutedColor);
                                    }
                                });
                            }
                        });

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
