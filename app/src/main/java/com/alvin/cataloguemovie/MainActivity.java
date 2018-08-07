package com.alvin.cataloguemovie;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alvin.cataloguemovie.Model.Movies.MovieResponse;
import com.alvin.cataloguemovie.Retrofit.ApiClient;
import com.alvin.cataloguemovie.Adapter.RecyclerMovieAdapter;
import com.alvin.cataloguemovie.Model.Movies.MovieResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String LANGUAGE = "en-US";
    private int currentPage = 1;

    private RecyclerMovieAdapter adapter;

    private Call<MovieResponse> call;
    private ApiClient apiClient = null;
    private List<MovieResult> moviesItem;

    @BindView(R.id.recycler_movie)
    RecyclerView recyclerView;

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        setSupportActionBar(homeToolbar);
        getSupportActionBar().setTitle("Movie Catalogue");

        getMovie();

    }

    private void getMovie() {
        apiClient = ApiClient.getInstance();
        call = apiClient.getApi().getPopularMovies(API_KEY, LANGUAGE, currentPage);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    moviesItem = response.body().getMovieResults();
                    if (moviesItem != null) {
                        progressBar.setVisibility(View.GONE);
                        adapter = new RecyclerMovieAdapter(MainActivity.this, moviesItem);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        // Get the SearchView and set the searchable configuration
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName = new ComponentName(getApplicationContext(), SearchableActivity.class);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
}
