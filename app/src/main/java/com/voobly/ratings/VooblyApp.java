package com.voobly.ratings;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.voobly.ratings.data.local.Preferencias;

/**
 * Created by jvazquez on 24/04/2017.
 */

public class VooblyApp extends Application {
    private static VooblyApp m_singleton;
    private Preferencias pref;

    @Override
    public void onCreate() {
        super.onCreate();
        m_singleton = this;
        pref = new Preferencias(this);
        Fresco.initialize(this);
    }
    public static VooblyApp getInstance(){
        return m_singleton;
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public Preferencias getPref() {
        return pref;
    }
}
