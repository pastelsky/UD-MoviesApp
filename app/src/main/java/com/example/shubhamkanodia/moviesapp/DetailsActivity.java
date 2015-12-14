package com.example.shubhamkanodia.moviesapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.shubhamkanodia.moviesapp.Pojos.Results;
import com.example.shubhamkanodia.moviesapp.R;
import com.example.shubhamkanodia.moviesapp.databinding.ActivityDetailsBinding;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageView ivMask = (ImageView) findViewById(R.id.mask);
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams) ivMask.getLayoutParams();
        lp.setParallaxMultiplier(0.05f);
        ivMask.setLayoutParams(lp);

        Results selectedMovie = (Results) getIntent().getSerializableExtra("selected_movie");
        binding.setMovie(selectedMovie);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
