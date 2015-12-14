package com.example.shubhamkanodia.moviesapp.Trivials;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shubhamkanodia on 06/12/15.
 */
public class Utils {


    public static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity)cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)cont).getBaseContext());

        return null;
    }

    public static String formatDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat toFormat = new SimpleDateFormat("dd MMMM yy");

        Date d = new Date();

        try{
            d = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toFormat.format(d);
    }


}
