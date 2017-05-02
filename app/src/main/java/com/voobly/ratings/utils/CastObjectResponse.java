package com.voobly.ratings.utils;

import android.util.Log;

import com.voobly.ratings.data.model.UserVoobly;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvazquez on 26/04/2017.
 */

public class CastObjectResponse {

    public static <T> List<T> castVooblyResponseToObject(Class<T> object, String stringResponse){
        String[] splitNames = stringResponse.split("\n");
        List<T> listObjects = new ArrayList<>();
        try {
            if(splitNames.length > 1){
                String[] names = splitNames[0].split(",");
                for(int i = 1; i < splitNames.length; i++){
                    Constructor constructor = object.getConstructor();
                    T instance = (T) constructor.newInstance();
                    String[] values = splitNames[i].split(",");
                    for(int j = 0; j < names.length; j++){
                        try {
                            Field field = instance.getClass().getDeclaredField(names[j]);
                            field.setAccessible(true);
                            field.set(instance, values[j]);
                            field.setAccessible(false);
                        } catch (Exception e){
                            e.printStackTrace();
                            try {
                                Log.d("ErrorCasteo", "No agregados - NOMBRE: " + names[j] + " OBJETO: " + instance.getClass().getField("name").get(instance));
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    listObjects.add(instance);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return listObjects;
    }

}
