package com.voobly.ratings.data.remote.network.model;

import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.remote.EndPoint;
import com.voobly.ratings.data.remote.interfaces.IRequestResult;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by jvazquez on 24/04/2017.
 */

public class WsRequest {

    public String                  identifier;
    public int                     method;
    public HashMap<String,String>  headers;
    public String                  url_request;
    public Object                  body;
    public IRequestResult          requestResult;
    public int                     timeOut;
    public Type                    typeResponse;
    public EndPoint endPoint;

    public WsRequest(String identifier, EndPoint endPoint, int method, HashMap<String, String> headers, String url_request, Object body, IRequestResult requestResult, int timeOut, Type typeResponse){
        this.identifier     = identifier;
        this.endPoint       = endPoint;
        this.method         = method;
        this.headers        = headers;
        this.url_request    = url_request;
        this.body           = body;
        this.requestResult  = requestResult;
        this.timeOut        = timeOut;
        this.typeResponse   = typeResponse;
    }


}
