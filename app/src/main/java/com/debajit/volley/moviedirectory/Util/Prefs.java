package com.debajit.volley.moviedirectory.Util;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by debaj on 3/12/2018.
 */

public class Prefs {

    SharedPreferences sharedPreferences;

    public Prefs(Activity activity){
        sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public void setSearch(String search){
        sharedPreferences.edit().putString("search",search).commit();
    }

    public String getSearch(){
        return sharedPreferences.getString("search","Batman");
    }

}
