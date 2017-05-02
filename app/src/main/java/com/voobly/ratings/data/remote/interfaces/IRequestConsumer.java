package com.voobly.ratings.data.remote.interfaces;

import com.voobly.ratings.data.remote.network.model.WsRequest;

/**
 * Created by jvazquez on 24/04/2017.
 */

public interface IRequestConsumer {

    void sendJsonRequest(WsRequest request);

    void sendStringRequest(WsRequest request);
}
