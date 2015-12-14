package com.example.shubhamkanodia.moviesapp.Pojos;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.shubhamkanodia.moviesapp.R;

import com.example.shubhamkanodia.moviesapp.Trivials.PaletteTransformation;
import com.example.shubhamkanodia.moviesapp.Trivials.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shubhamkanodia on 23/11/15.
 */
public class Results extends BaseObservable implements Serializable {

    public String vote_average;

    public String backdrop_path;

    public String adult;

    public String id;

    public String title;

    public String original_language;

    public String overview;

    public String[] genre_ids;

    public String original_title;

    public String release_date;

    public String vote_count;

    public String poster_path;

    public String video;

    public String popularity;

    public
    @Bindable
    String accentcolor;

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {

return release_date;

    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "ClassPojo [vote_average = " + vote_average + ", backdrop_path = " + backdrop_path + ", adult = " + adult + ", id = " + id + ", title = " + title + ", original_language = " + original_language + ", overview = " + overview + ", genre_ids = " + genre_ids + ", original_title = " + original_title + ", release_date = " + release_date + ", vote_count = " + vote_count + ", poster_path = " + poster_path + ", video = " + video + ", popularity = " + popularity + "]";
    }

    public String getAccentcolor() {
        return accentcolor;
    }

    public void setAccentcolor(String accentcolor) {
        this.accentcolor = accentcolor;
    }
}