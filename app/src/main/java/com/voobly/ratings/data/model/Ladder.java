package com.voobly.ratings.data.model;

import android.support.annotation.NonNull;

/**
 * Created by jvazquez on 03/05/2017.
 */

public class Ladder  implements Comparable<String>{

    private String id            = "";
    private String lobby         = "";
    private String rank          = "";
    private String uid           = "";
    private String display_name  = "";
    private String rating        = "";
    private String wins          = "";
    private String losses        = "";
    private String streak        = "";

    public Ladder(){
        id            = "";
        lobby         = "";
        rank          = "";
        uid           = "";
        display_name  = "";
        rating        = "";
        wins          = "";
        losses        = "";
        streak        = "";
    }

    @Override
    public int compareTo(@NonNull String o) {
        return getId().equals(o) ? 1 : -1;
    }
    public String getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public String getLobby() {
        return lobby;
    }

    public String getUid() {
        return uid;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getRating() {
        return rating;
    }

    public String getWins() {
        return wins;
    }

    public String getLosses() {
        return losses;
    }

    public String getStreak() {
        return streak;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLobby(String lobby) {
        this.lobby = lobby;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

}
