package com.voobly.ratings.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvazquez on 17/05/2017.
 */

public class Rss implements Serializable {
    private String version = "";
    private Channel channel = new Channel();

    public Rss(){

    }

    public Channel getChannel() {
        return channel;
    }

    public String getVersion() {
        return version;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
