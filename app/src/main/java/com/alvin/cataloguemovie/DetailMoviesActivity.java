package com.alvin.cataloguemovie;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alvin.cataloguemovie.Retrofit.ApiClient;
import com.alvin.cataloguemovie.Model.Detail.DetailMovie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMoviesActivity extends AppCompatActivity {

    private static final String TAG = "DetailMoviesActivity";

    private static final String IMAGE_BASE_URL = BuildConfig.IMAGE_BASE_URL;
    private static final String API_KEY = BuildConfig.API_KEY;
    public static String MOVIE_ID = "movie_id";

    private int movie_id;
    private int mutedColor = R.attr.colorPrimary;
    private ProgressDialog mProgress;

    private ApiClient apiClient = null;
    private Call<DetailMovie> detailMovieCall;
    private DetailMovie detailMovie;

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

        apiClient = ApiClient.getInstance();

        mProgress = new ProgressDialog(this);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ctl.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        movie_id = getIntent().getIntExtra(MOVIE_ID, movie_id);

        Log.d(TAG, "onCreate: " + movie_id);

        getDetailMovie();
    }

    private void getDetailMovie() {
        mProgress.setMessage(getString(R.string.progress_loading));
        mProgress.show();
        mProgress.setCanceledOnTouchOutside(false);
        detailMovieCall = apiClient.getApi().getDetailMovies(movie_id, API_KEY);
        detailMovieCall.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {

                if (response.isSuccessful()) {
                    detailMovie = response.body();

                    mProgress.dismiss();

                    String poster_url = IMAGE_BASE_URL + "w342" + detailMovie.getPosterPath();
                    String backdrop_url = IMAGE_BASE_URL + "w780" + detailMovie.getBackdropPath();

                    movieTitleBig.setText(detailMovie.getTitle());
                    movieYear.setText(detailMovie.getReleaseDate().split("-")[0]);

                    //buat set apabila taglinenya kosong
                    if (!detailMovie.getTagline().isEmpty()) {
                        movieTagline.setText("\"" + detailMovie.getTagline() + "\"");
                    } else {
                        movieTagline.setText(R.string.no_tagline);
                    }

                    movieRate.setText(detailMovie.getVoteAverage().toString());
                    movieReleaseStatus.setText(detailMovie.getStatus());
                    movieReleaseDate.setText(dateFormat(detailMovie.getReleaseDate()));
                    movieRuntime.setText(detailMovie.getRuntime() + getString(R.string.minute));
                    movieOverview.setText(detailMovie.getOverview());
                    movieLanguage.setText(detailMovie.getOriginalLanguage());
                    movieHomepage.setText(detailMovie.getHomepage());

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
                    ctl.setTitle(detailMovie.getTitle());

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
                                            mutedColor = palette.getMutedColor(R.attr.colorPrimary);
                                            ctl.setContentScrimColor(mutedColor);
                                        }
                                    });
                                }
                            });

                }


            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Toast.makeText(DetailMoviesActivity.this, R.string.toast_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private String dateFormat(String oldDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(oldDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String finalDate = newFormat.format(myDate);

        return finalDate;

    }

}
