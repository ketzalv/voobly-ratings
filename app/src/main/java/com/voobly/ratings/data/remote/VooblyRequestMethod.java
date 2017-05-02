package com.voobly.ratings.data.remote;

import com.android.volley.Request;
import com.voobly.ratings.data.DataManager;
import com.voobly.ratings.data.remote.interfaces.IRequestResult;
import com.voobly.ratings.data.remote.network.ConstructRequest;

import static com.voobly.ratings.data.remote.network.ConstructRequest.constructUrlRequest;
import static com.voobly.ratings.utils.Recursos.ID_AOE_TC;
import static com.voobly.ratings.utils.Recursos.TIMEOUT;

/**
 * Created by jvazquez on 24/04/2017.
 */

public class VooblyRequestMethod {
    public static void getUserInformation(IRequestResult result, String udid){
        ConstructRequest.consumeStringWS(EndPoint.USER, Request.Method.GET, null, constructUrlRequest(EndPoint.USER, udid), "", result, TIMEOUT, String.class);
    }
//    public static void getUserLadderInformation(IRequestResult result, String udid){
//        ConstructRequest.consumeStringWS(DataManager.EndPoint.USER);
//    }
    public static void findUser(IRequestResult result, String query){
        ConstructRequest.consumeStringWS(EndPoint.USER_FINDER, Request.Method.GET, null, constructUrlRequest(EndPoint.USER, query), "", result, TIMEOUT, String.class);
    }
    public static void getLobbies(IRequestResult result){
        ConstructRequest.consumeStringWS(EndPoint.LOBBIES, Request.Method.GET, null, constructUrlRequest(EndPoint.LOBBIES, String.valueOf(ID_AOE_TC)), "", result, TIMEOUT, String.class);
    }

}
