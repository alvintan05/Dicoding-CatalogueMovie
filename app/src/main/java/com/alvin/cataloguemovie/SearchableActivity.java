package com.alvin.cataloguemovie;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alvin.cataloguemovie.Adapter.RecyclerSearchAdapter;
import com.alvin.cataloguemovie.Model.Search.SearchMovieResult;
import com.alvin.cataloguemovie.Retrofit.ApiClient;
import com.alvin.cataloguemovie.Retrofit.OnGetSearchCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchableActivity extends AppCompatActivity {

    private static final String TAG = "SearchableActivity";

    private static final String LANGUAGE = "en-US";

    private String querySearch = null;

    private ApiClient apiClient;

    private RecyclerSearchAdapter adapter;

    private ProgressDialog mProgress;

    @BindView(R.id.recycler_search_movie)
    RecyclerView recyclerViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        Log.d(TAG, "onCreate: started");
        ButterKnife.bind(this);

        mProgress = new ProgressDialog(this);

        apiClient = ApiClient.getInstance();

        getSearchResult();

    }

    private void getSearchResult () {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            querySearch = intent.getStringExtra(SearchManager.QUERY);
        }
        apiClient.getSearchMoviesItem(LANGUAGE, querySearch, new OnGetSearchCallback() {
            @Override
            public void onSuccess(List<SearchMovieResult> searchMoviesItem) {
                Log.d(TAG, "onSuccess: called");
                adapter = new RecyclerSearchAdapter(SearchableActivity.this, searchMoviesItem);
                recyclerViewSearch.setAdapter(adapter);
                recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewSearch.setHasFixedSize(true);
            }

            @Override
            public void onError() {
                Toast.makeText(SearchableActivity.this, "Movie not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
