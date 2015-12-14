package com.example.shubhamkanodia.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.shubhamkanodia.moviesapp.Pojos.Results;
import com.example.shubhamkanodia.moviesapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shubhamkanodia on 23/11/15.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {
    private final List<Results> movies;
    static Context context;
    static private RecyclerViewClickListener itemClicker;
        private View empty;



public MovieAdapter(List<Results> movies) {
        this.movies = movies;
    }


    public static class BindingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);

            v.setOnClickListener(this);
            binding = DataBindingUtil.bind(v);

        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {

            itemClicker.recyclerViewListClicked(v, getAdapterPosition());
        }
    }

    public MovieAdapter(Context context, RecyclerViewClickListener r, List<Results> movies,  View emptyView) {
        this.movies = movies;
        this.context = context;
        this.itemClicker = r;
        this.empty = emptyView;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_tile, parent, false);
        BindingHolder holder = new BindingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Results movie = movies.get(position);
        holder.getBinding().setVariable(com.example.shubhamkanodia.moviesapp.BR.movie, movie);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onViewRecycled(BindingHolder holder) {
        super.onViewRecycled(holder);
    }

    public void dataSetChanged() {
        this.notifyDataSetChanged();
        Log.e("ADAP", movies.size() + "");
        this.empty.setVisibility(this.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public void setMovies(List<Results> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

        @UiThread
    public void clearItems() {
        this.movies.clear();
        this.dataSetChanged();
    }
    public interface RecyclerViewClickListener
    {
        public void recyclerViewListClicked(View v, int position);
    }
}
