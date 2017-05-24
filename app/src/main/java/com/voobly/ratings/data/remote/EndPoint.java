package com.voobly.ratings.data.remote;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 25/04/2017.
 */

public enum EndPoint {

    USER("user/"),
    USER_FINDER("finuser/"),
    LADDER("ladder/"),
    LOBBIES("lobbies/"),
    FORUM_ANNOUNCEMENTS("588");

    private String name;
    private EndPoint(String name){
       this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
