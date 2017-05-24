package com.voobly.ratings.data.remote;

import com.android.volley.Request;
import com.voobly.ratings.data.remote.interfaces.IRequestResult;
import com.voobly.ratings.data.remote.network.BuildRequest;
import com.voobly.ratings.data.remote.network.WsCaller;

import static com.voobly.ratings.data.remote.network.BuildRequest.buildUrlWS;
import static com.voobly.ratings.data.remote.network.BuildRequest.buldUrlFeeds;
import static com.voobly.ratings.utils.Constants.ID_AOE_TC;
import static com.voobly.ratings.utils.Constants.TIMEOUT;

/**
 * Created by jvazquez on 24/04/2017.
 */

public class VooblyRequestMethod {
    public static void getUserInformation(IRequestResult result, String udid){
        BuildRequest.consumeStringWS("",EndPoint.USER, Request.Method.GET, null, buildUrlWS(EndPoint.USER, udid, 0), "", result, TIMEOUT, String.class);
    }


    public static void findUser(IRequestResult result, String query){
        BuildRequest.consumeStringWS("",EndPoint.USER_FINDER, Request.Method.GET, null, buildUrlWS(EndPoint.USER, query, 0), "", result, TIMEOUT, String.class);
    }
    public static void getLobbies(IRequestResult result){
        BuildRequest.consumeStringWS("",EndPoint.LOBBIES, Request.Method.GET, null, buildUrlWS(EndPoint.LOBBIES, String.valueOf(ID_AOE_TC), 0), "", result, TIMEOUT, String.class);
    }

    public static void getTopLadders(String lobby, String ladder, IRequestResult result){
        BuildRequest.consumeStringWS(lobby,EndPoint.LADDER, Request.Method.GET, null, buildUrlWS(EndPoint.LADDER, ladder, 5), "", result, TIMEOUT, String.class);
    }
    public static void getFeed(EndPoint forum, IRequestResult result){
        BuildRequest.consumeFeed(forum.toString(), forum, Request.Method.GET, null, buldUrlFeeds(forum), "", result, TIMEOUT, String.class);
    }
}
