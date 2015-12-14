package com.example.shubhamkanodia.moviesapp.Trivials;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.shubhamkanodia.moviesapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by shubhamkanodia on 14/12/15.
 */
public class CustomXMLAttributes {


    @BindingAdapter({"bind:posterId"})
    public static void loadPosterImage(final ImageView view, String posterId) {

        Picasso.with(view.getContext())
                .load("http://image.tmdb.org/t/p/w500/" + posterId)
                .transform(PaletteTransformation.instance())
                .placeholder(R.drawable.placeholder)
                .into(view, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap(); // Ew!
                        Palette palette = PaletteTransformation.getPalette(bitmap);
                        FrameLayout viewParent = (FrameLayout) view.getParent();
                        RelativeLayout titleBack = (RelativeLayout) viewParent.findViewById(R.id.title_background);
                        titleBack.setBackgroundColor(palette.getDarkMutedColor(palette.getMutedColor(palette.getDarkVibrantColor(0x000000))));
                        titleBack.getBackground().setAlpha(200);
                    }
                });

    }

    @BindingAdapter({"bind:backdropId"})
    public static void loadBackdropImage(final ImageView view, String posterId) {

        Picasso.with(view.getContext())
                .load("http://image.tmdb.org/t/p/w780/" + posterId)
                .transform(PaletteTransformation.instance())
                .placeholder(R.drawable.placeholder)
                .into(view, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap(); // Ew!
                        Palette palette = PaletteTransformation.getPalette(bitmap);

                        final ViewGroup viewGroup = (ViewGroup) view.getParent().getParent().getParent();
                        Log.e("ID::", viewGroup.getId() + "" + viewGroup.getLayerType());

                        final FloatingActionButton fb = (FloatingActionButton) viewGroup.findViewById(R.id.fab);
                        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), 0xffd7285f, palette.getVibrantColor(palette.getLightVibrantColor(0xFF333333)));;

                        fb.setScaleX(1);
                        fb.setScaleY(1);
                        fb.setBackgroundTintList(ColorStateList.valueOf(palette.getVibrantColor(palette.getDarkVibrantColor(0xFF333333))));


                        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.grow);
                        fb.startAnimation(animation);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = Utils.scanForActivity(view.getContext()).getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                            window.setStatusBarColor(palette.getDarkMutedColor(palette.getMutedColor(0xFF333333)));
                        }
                    }
                });

    }

}
