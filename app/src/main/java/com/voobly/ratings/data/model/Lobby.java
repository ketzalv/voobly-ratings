package com.voobly.ratings.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvazquez on 02/05/2017.
 */

public class Lobby {
    private String          lobbyid         = "";
    private String          name            = "";
    private String          players_online  = "";
    private String          max_players     = "";
    private List<String>    ladders         = new ArrayList<>();

    public Lobby(){
        lobbyid          = "";
        name             = "";
        players_online   = "";
        max_players      = "";
        ladders          = new ArrayList<>();
    }

    public List<String> getLadders() {
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

    public void setLadders(List<String> ladders) {
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
