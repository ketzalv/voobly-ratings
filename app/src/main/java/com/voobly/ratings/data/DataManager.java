package com.voobly.ratings.data;

import com.voobly.ratings.data.remote.EndPoint;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 *
 * Created by jvazquez on 20/04/2017.
 */

public class DataManager {

    private String identifier;
    private boolean status;
    private Object  data;
    private Origin origin;
    private EndPoint endPoint;

    public DataManager(String identifier, boolean status, Object data, Origin origin, EndPoint endPoint){
        this.identifier = identifier;
        this.status     = status;
        this.data       = data;
        this.origin     = origin;
        this.endPoint   = endPoint;
    }

    public enum Origin {
        LOCAL, NETWORK
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Object getData() {
        return data;
    }

    public EndPoint getEndPoint() {
        return endPoint;
    }

    public Origin getOrigin() {
        return origin;
    }

    public boolean getStatus() {
        return status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}