package com.voobly.ratings.data;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public class DataManagerEvent {
    private String event = "";
    private Object data;

    public DataManagerEvent(String event, Object data){
        this.event = event;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getEvent() {
        return event;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
