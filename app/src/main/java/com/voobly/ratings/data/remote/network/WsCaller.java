package com.voobly.ratings.data.remote.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.GsonBuilder;
import com.voobly.ratings.R;
import com.voobly.ratings.VooblyApp;
import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.remote.interfaces.IRequestConsumer;
import com.voobly.ratings.data.remote.network.model.WsRequest;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by jvazquez on 24/04/2017.
 */

public class WsCaller implements IRequestConsumer {

    @Override
    public void sendJsonRequest(final WsRequest request) {
        VolleySingleton volleySingleton = VolleySingleton.getInstance(VooblyApp.getInstance().getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                request.method, request.url_request,
                (JSONObject) request.body,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                request.requestResult.onSuccess(new DataManager(request.identifier,
                        true,
                        jsonToObject(response.toString(), request.typeResponse),
                        DataManager.Origin.NETWORK,
                        request.endPoint));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                request.requestResult.onFailed(new DataManager(request.identifier,
                        false,
                        getMessage(error.getClass().getName()),
                        DataManager.Origin.NETWORK,
                        request.endPoint));
            }
        }
        );
        volleySingleton.getRequestQueue().add(jsonObjectRequest);
    }

    @Override
    public void sendStringRequest(final WsRequest request) {
        VolleySingleton volleySingleton = VolleySingleton.getInstance(VooblyApp.getInstance().getApplicationContext());

        StringRequest stringRequest = new StringRequest(request.method, request.url_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                request.requestResult.onSuccess(new DataManager(request.identifier,
                        true,
                        response,
                        DataManager.Origin.NETWORK,
                        request.endPoint));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                request.requestResult.onFailed(new DataManager(request.identifier,
                        false,
                        getMessage(error.getClass().getName()),
                        DataManager.Origin.NETWORK,
                        request.endPoint));
            }
        });
        volleySingleton.getRequestQueue().add(stringRequest);
    }

    private static Object jsonToObject(String json, Type type){

        Object o = null;
        try {
            o = new GsonBuilder()
                    .setDateFormat("dd-MM-yyyy HH:mm:ss")
                    .create().
                            fromJson(json, type);
        } catch (Exception e) {
            o=null;
        }
        return o;
    }
    private static String getMessage(String name) {
        Context context = VooblyApp.getInstance().getApplicationContext();
        switch (name){
            case "com.android.volley.TimeoutError":
                return context.getString(R.string.ws_error_timeout);
            case "com.android.volley.NoConnectionError":
                return context.getString(R.string.ws_error_network);
            case "com.android.volley.NetworkError":
                return context.getString(R.string.ws_error_network);
            default:
                return context.getString(R.string.ws_error_unknow);
        }
    }
}
