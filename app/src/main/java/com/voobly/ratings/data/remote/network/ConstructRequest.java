package com.voobly.ratings.data.remote.network;

import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.remote.EndPoint;
import com.voobly.ratings.data.remote.interfaces.IRequestResult;
import com.voobly.ratings.data.remote.network.model.WsRequest;
import com.voobly.ratings.utils.Recursos;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by jvazquez on 24/04/2017.
 */

public class ConstructRequest {

    public static void consumeJsonWS(EndPoint endPoint, int method, HashMap<String, String> headers, String url, Object body, IRequestResult result, int timeout, Type response){
        WsCaller call = new WsCaller();
        call.sendJsonRequest(constructRequest(endPoint, method, headers, url, body,  result, timeout, response));
    }
    public static void consumeStringWS(EndPoint endPoint, int method, HashMap<String, String> headers, String url, Object body, IRequestResult result, int timeout, Type response){
        WsCaller call = new WsCaller();
        call.sendStringRequest(constructRequest(endPoint, method, headers, url, body,  result, timeout, response));
    }
    private static WsRequest constructRequest(EndPoint endPoint, int method, HashMap<String, String> headers, String url, Object body, IRequestResult result, int timeout, Type response){
        if(body == null){
            throw new RuntimeException("body empty");
        }
        return new WsRequest(endPoint, method, headers, url, body, result, timeout, response);
    }
    public static String constructUrlRequest(EndPoint endPoint, String query){
        return Recursos.URL_SERVER + endPoint.toString() + query.replace(" ", "+") + Recursos.API_KEY;
    }
}
