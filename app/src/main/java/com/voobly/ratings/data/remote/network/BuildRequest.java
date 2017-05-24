package com.voobly.ratings.data.remote.network;

import com.voobly.ratings.data.remote.EndPoint;
import com.voobly.ratings.data.remote.interfaces.IRequestResult;
import com.voobly.ratings.data.remote.network.model.WsRequest;
import com.voobly.ratings.utils.Constants;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by jvazquez on 24/04/2017.
 */

public class BuildRequest {

    public static void consumeJsonWS(String identifier, EndPoint endPoint, int method, HashMap<String, String> headers, String url, Object body, IRequestResult result, int timeout, Type response){
        WsCaller call = new WsCaller();
        call.sendJsonRequest(constructRequest(identifier, endPoint, method, headers, url, body,  result, timeout, response));
    }
    public static void consumeStringWS(String identifier, EndPoint endPoint, int method, HashMap<String, String> headers, String url, Object body, IRequestResult result, int timeout, Type response){
        WsCaller call = new WsCaller();
        call.sendStringRequest(constructRequest(identifier, endPoint, method, headers, url, body,  result, timeout, response));
    }
    public static void consumeFeed(String identifier, EndPoint endPoint, int method, HashMap<String, String> headers, String url, Object body, IRequestResult result, int timeout, Type response){
        WsCaller call = new WsCaller();
        call.sendStringRequest(constructRequest(identifier, endPoint, method, headers, url, body,  result, timeout, response));
    }
    private static WsRequest constructRequest(String identifier, EndPoint endPoint, int method, HashMap<String, String> headers, String url, Object body, IRequestResult result, int timeout, Type response){
        if(body == null){
            throw new RuntimeException("body empty");
        }
        return new WsRequest(identifier, endPoint, method, headers, url, body, result, timeout, response);
    }

    public static String buildUrlWS(EndPoint endPoint, String query, int limit){
        return Constants.URL_SERVER + endPoint.toString() + query.replace(" ", "+") + Constants.API_KEY + (limit == 0 ? "":"&limit=" + limit);
    }
    public static String buldUrlFeeds(EndPoint endPoint){
        return Constants.URL_FEEDS + endPoint.toString() + Constants.USER_SESSIONS;
    }
}
