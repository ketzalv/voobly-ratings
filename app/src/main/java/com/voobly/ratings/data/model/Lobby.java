package com.voobly.ratings.data.model;

/**
 * Created by jvazquez on 02/05/2017.
 */

public class Lobby {
    public String lobbyid          = "";
    public String name             = "";
    public String players_online   = "";
    public String max_players      = "";
    public String ladders          = "";

    public Lobby(){
        lobbyid          = "";
        name             = "";
        players_online   = "";
        max_players      = "";
        ladders          = "";
    }

    public String getLadders() {
        return ladders;
    }

    public String getLobbyid() {
        return lobbyid;
    }

    public String getMax_players() {
        return max_players;
    }

    public String getName() {
        return name;
    }

    public String getPlayers_online() {
        return players_online;
    }

    public void setLadders(String ladders) {
        this.ladders = ladders;
    }

    public void setLobbyid(String lobbyid) {
        this.lobbyid = lobbyid;
    }

    public void setMax_players(String max_players) {
        this.max_players = max_players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers_online(String players_online) {
        this.players_online = players_online;
    }
}
