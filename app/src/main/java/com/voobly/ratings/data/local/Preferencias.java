package com.voobly.ratings.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static com.voobly.ratings.utils.Utils.objectToString;
import static com.voobly.ratings.utils.Utils.stringToObject;


public class Preferencias {

    private SharedPreferences preferences;

    public Preferencias(Context context ){
        this.preferences  = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveData (String key, String data){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString(key,data);
        editor.commit();
    }

    public void saveData (String key, Serializable data){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString(key, objectToString(data));
        editor.commit();
    }

    public void saveDataBool (String key, boolean data){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }

    public boolean containsData(String key){
        return this.preferences.contains(key);
    }

    public Serializable loadData(String key, Boolean isObject ){
        return stringToObject(this.preferences.getString(key, null));
    }
    public <T extends Serializable> T loadData(String key, Class<T> type ){
        return type.cast(stringToObject(this.preferences.getString(key, null)));
    }

    public String loadString(String key){
        return preferences.getString(key, "");
    }


    public void clearPreferences(){
		SharedPreferences.Editor editor = this.preferences.edit();
		editor.clear();
		editor.commit();
		return;
    }

    public void clearPreference(String key){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.remove(key);
        editor.commit();
        return;
    }

    public void saveDataInt (String key, int data){
        SharedPreferences.Editor editor = this.preferences.edit();
        String stringData = Integer.toString(data);
        editor.putString(key,stringData);
        editor.commit();
    }
}

