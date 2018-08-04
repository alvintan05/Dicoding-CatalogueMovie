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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alvin.cataloguemovie.Retrofit.ApiClient;
import com.alvin.cataloguemovie.Adapter.RecyclerMovieAdapter;
import com.alvin.cataloguemovie.Retrofit.OnGetMoviesCallback;
import com.alvin.cataloguemovie.Model.Popular.PopularMovieResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ApiClient apiClient;

    private RecyclerMovieAdapter adapter;

    private Context context;

    @BindView(R.id.recycler_movie)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        ButterKnife.bind(this);

        apiClient = ApiClient.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getMovieItem();
    }


    private void getMovieItem() {
        apiClient.getMoviesItem(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<PopularMovieResult> moviesItem) {
                adapter = new RecyclerMovieAdapter(MainActivity.this, moviesItem);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Error koneksi internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName = new ComponentName(this, SearchableActivity.class);
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
