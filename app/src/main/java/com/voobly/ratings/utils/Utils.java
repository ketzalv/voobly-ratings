package com.voobly.ratings.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by jvazquez on 02/05/2017.
 */

public class Utils {

    public static String objectToString(Serializable obj) {

        if (obj == null)
            return "";

        try {
            ByteArrayOutputStream serialObj = new ByteArrayOutputStream();
            ObjectOutputStream objStream;
            objStream = new ObjectOutputStream(serialObj);
            objStream.writeObject(obj);
            objStream.close();
            return Codec.asHexStr(serialObj.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
    public static Serializable stringToObject(String str) {

        if (str == null || str.length() == 0)
            return null;
        try {
            ByteArrayInputStream serialObj = new ByteArrayInputStream(
                    Codec.asBytes(str));
            ObjectInputStream objStream;
            objStream = new ObjectInputStream(serialObj);
            return (Serializable) objStream.readObject();
        } catch (Exception e) {
            return null;
        }

    }
    public static void showToast(String message, Context context){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }
    public static void showSnackBar(View coordinatorLayout, String message, String actionName, View.OnClickListener listener){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).setAction(actionName, listener).show();
    }
    public static void showSnackBar(View coordinatorLayout, String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }
    public static void showSnackBar(View coordinatorLayout, String message, String actionName, int duration, View.OnClickListener listener){
        Snackbar.make(coordinatorLayout, message, duration).setAction(actionName, listener).show();
    }
}
