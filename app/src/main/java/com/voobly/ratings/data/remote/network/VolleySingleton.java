package com.voobly.ratings.data.remote.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 *
 * Created by jvazquez on 20/04/2017.
 */

class VolleySingleton {

    private static VolleySingleton ourInstance;
    private RequestQueue mRequestQueue;
    private Context mCtx;

    public synchronized static VolleySingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new VolleySingleton(context);
        }
        return ourInstance;
    }

    private VolleySingleton(Context context) {
        mCtx = context;
    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
