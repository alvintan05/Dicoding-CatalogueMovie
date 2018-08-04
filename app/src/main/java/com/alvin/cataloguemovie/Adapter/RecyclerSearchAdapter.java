package com.alvin.cataloguemovie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvin.cataloguemovie.BuildConfig;
import com.alvin.cataloguemovie.DetailMoviesActivity;
import com.alvin.cataloguemovie.Model.Search.SearchMovieResult;
import com.alvin.cataloguemovie.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alvin Tandiardi on 04/08/2018.
 */

public class RecyclerSearchAdapter extends RecyclerView.Adapter<RecyclerSearchAdapter.SearchViewHolder> {

    private static final String TAG = "RecyclerSearchAdapter";

    private final static String IMAGE_BASE_URL = BuildConfig.IMAGE_BASE_URL;

    private Context mContext;

    private List<SearchMovieResult> searchMovieResults;

    public RecyclerSearchAdapter(Context mContext, List<SearchMovieResult> searchMovieResults) {
        this.mContext = mContext;
        this.searchMovieResults = searchMovieResults;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie_list, parent, false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        String poster_url = IMAGE_BASE_URL + "w185" + searchMovieResults.get(position).getPosterPath();

        final int movie_id = searchMovieResults.get(position).getId();

        Log.d(TAG, "url image : " + poster_url);

        Glide.with(mContext.getApplicationContext())
                .load(poster_url)
                .error(R.drawable.example)
                .into(holder.imgMoviePoster);

        holder.tvMovieTitle.setText(searchMovieResults.get(position).getTitle());
        holder.tvMovieDescription.setText(searchMovieResults.get(position).getOverview());
        holder.tvMovieDate.setText(searchMovieResults.get(position).getReleaseDate());

        holder.parentMovieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieIdIntent = new Intent(mContext, DetailMoviesActivity.class);
                movieIdIntent.putExtra(DetailMoviesActivity.MOVIE_ID, movie_id);
                mContext.startActivity(movieIdIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchMovieResults.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_movie_card)
        CardView parentMovieCard;

        @BindView(R.id.item_movie_poster)
        ImageView imgMoviePoster;

        @BindView(R.id.item_movie_title)
        TextView tvMovieTitle;

        @BindView(R.id.item_movie_description)
        TextView tvMovieDescription;

        @BindView(R.id.item_movie_date)
        TextView tvMovieDate;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}


