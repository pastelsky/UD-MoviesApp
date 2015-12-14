package com.example.shubhamkanodia.moviesapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shubhamkanodia.moviesapp.Adapters.MovieAdapter;
import com.example.shubhamkanodia.moviesapp.ApiServices.TheMovieDBService;
import com.example.shubhamkanodia.moviesapp.Pojos.MovieResults;
import com.example.shubhamkanodia.moviesapp.Pojos.Results;
import com.example.shubhamkanodia.moviesapp.Trivials.SpaceItemDecoration;
import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.squareup.okhttp.OkHttpClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {


    private static String API_KEY;
    private static final String SORT_ORDER_POPULARITY = "popularity.desc";
    private static final String SORT_ORDER_RATING = "vote_average.desc";
    private static final int MINIMUM_VOTE_COUNT = 200;


    RecyclerView rvMovies;
    List<Results> movies = new ArrayList<Results>();

    MovieAdapter.RecyclerViewClickListener itemListener;
    MovieAdapter mAdapter;
    LinearLayout lvempty;
    ProgressBar pbLoading;

    Retrofit retrofit;
    TheMovieDBService service;
    String currentSortOrder = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        API_KEY = getString(R.string.api_key);

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        lvempty = (LinearLayout) findViewById(R.id.lvEmpty);
        pbLoading = (ProgressBar) findViewById(R.id.toolbar_progress_bar);

        itemListener = new MovieAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {

                Gson gson = new Gson();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("selected_movie", movies.get(position));
                startActivity(intent);

            }
        };
        
        //Prepare the API Service

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        service = retrofit.create(TheMovieDBService.class);


        //Setup recycler view 
        
        rvMovies.addItemDecoration(new SpaceItemDecoration(getApplicationContext(), R.dimen.tile_padding));
        rvMovies.setLayoutManager(getLayoutManager(getResources().getConfiguration()));

        mAdapter = new MovieAdapter(this, itemListener, movies, lvempty);
        rvMovies.setAdapter(mAdapter);
        
        //Initially, load by popularity
        sortMoviesBy(SORT_ORDER_POPULARITY);


    }

    public GridLayoutManager getLayoutManager(final Configuration config) {
        // Checks the orientation of the screen and sets apppropriate grid

        GridLayoutManager layoutManager = new GridLayoutManager(this,
                config.orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 4,
                GridLayoutManager.VERTICAL,
                false);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
                    return position % 5 == 0 ? 2 : 1;
                else
                    return position % 7 == 0 ? 2 : 1;

            }
        });
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        return layoutManager;
    }

    public void sortMoviesBy(final String requestedSortMode) {

        if (!currentSortOrder.equalsIgnoreCase(requestedSortMode)) {

            pbLoading.setVisibility(View.VISIBLE);

            Call<MovieResults> popularityCall =
                    service.getMoviesBy(requestedSortMode, API_KEY, MINIMUM_VOTE_COUNT);

            popularityCall.enqueue(new Callback<MovieResults>() {
                @Override
                public void onResponse(Response<MovieResults> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        mAdapter.setMovies(new ArrayList<Results>(Arrays.asList(response.body().getResults())));
                        currentSortOrder = requestedSortMode;

                    } else {
                        Toast.makeText(getApplicationContext(), "Error fetching movies...", Toast.LENGTH_SHORT).show();
                    }
                    pbLoading.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        rvMovies.setLayoutManager(getLayoutManager(newConfig));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.menuSortPopularity) {
            sortMoviesBy(SORT_ORDER_POPULARITY);
            return true;
        } else if (id == R.id.menuSortRating) {
            sortMoviesBy(SORT_ORDER_RATING);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putSerializable("saved_movies_list", (Serializable) movies);

    }


}
