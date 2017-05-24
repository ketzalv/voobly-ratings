package com.voobly.ratings.data.local;

import com.voobly.ratings.utils.Constants;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public class DBContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER ";
    private static final String COMMA_SEP = ",";
    private static final String PRIMARY_KEY = " PRIMARY KEY(";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String INT_PRIMARY_KEY = " INTEGER NOT NULL PRIMARY KEY "; //AUTOINCREMENT";
    private static final String ALTER_TABLE = " ALTER TABLE ";
    private static final String ADD_COLUMN = " ADD COLUMN ";
    public static final String ISNT_NECESARY = "isnt_necesary";

    private DBContract(){

    }

    //TABLAS
    public static class Lobbies{
        public static final String TABLE_NAME = "Lobbies";


        public static final String COLUMN_ID                = "id";
        public static final String COLUMN_LOBBYID           = "lobbyid";
        public static final String COLUMN_NAME              = "name";
        public static final String COLUMN_PLAYERS_ONLINE    = "players_online";
        public static final String COLUMN_MAX_PLAYERS       = "max_players";
        public static final String COLUMN_LADDERS           = "ladders";
    }
    public static class Ladders{
        public static final String TABLE_NAME = "Ladders";


        public static final String COLUMN_ID                = "id";
        public static final String COLUMN_LOBBY             = "lobby";
        public static final String COLUMN_RANK              = "rank";
        public static final String COLUMN_UID               = "uid";
        public static final String COLUMN_DISPLAY_NAME      = "display_name";
        public static final String COLUMN_RATING            = "rating";
        public static final String COLUMN_WINS              = "wins";
        public static final String COLUMN_LOSSES            = "losses";
        public static final String COLUMN_STREAK            = "streak";
    }
    /**
     * Methods to create tables for v1
     * @see Constants#DB_VERSION
     */
    static String CREATE_TABLE_LOBBIES_v1(){
        return CREATE_TABLE + Lobbies.TABLE_NAME + " ( " +
                Lobbies.COLUMN_ID               + INT_PRIMARY_KEY   + COMMA_SEP +
                Lobbies.COLUMN_LOBBYID          + INT_TYPE          + COMMA_SEP +
                Lobbies.COLUMN_NAME             + TEXT_TYPE         + COMMA_SEP +
                Lobbies.COLUMN_PLAYERS_ONLINE   + INT_TYPE          + COMMA_SEP +
                Lobbies.COLUMN_MAX_PLAYERS      + INT_TYPE          + COMMA_SEP +
                Lobbies.COLUMN_LADDERS          + TEXT_TYPE         + " )";
    }
    static String CREATE_TABLE_LADDERS_v1(){
        return CREATE_TABLE + Ladders.TABLE_NAME + " ( " +
                Ladders.COLUMN_ID             + INT_TYPE  + COMMA_SEP +
                Ladders.COLUMN_LOBBY          + TEXT_TYPE + COMMA_SEP +
                Ladders.COLUMN_RANK           + INT_TYPE  + COMMA_SEP +
                Ladders.COLUMN_UID            + TEXT_TYPE + COMMA_SEP +
                Ladders.COLUMN_DISPLAY_NAME   + TEXT_TYPE + COMMA_SEP +
                Ladders.COLUMN_RATING         + INT_TYPE  + COMMA_SEP +
                Ladders.COLUMN_WINS           + INT_TYPE  + COMMA_SEP +
                Ladders.COLUMN_LOSSES         + INT_TYPE  + COMMA_SEP +
                Ladders.COLUMN_STREAK         + INT_TYPE  + " )";
    }
}
