package com.voobly.ratings.ui.base;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public class BaseEventsContract {
    //EVENTOS de mensajes
    public static final String EVENT_SHOW_PROGRESS          = "showdialog";
    public static final String EVENT_DISMISS_PROGRESS       = "dismissdialog";
    public static final String EVENT_SHOW_ERROR             = "showError";
    public static final String EVENT_SHOW_MESSAGE           = "showMessage";

    //Events in the adapter
    public static final String EVENT_LOBBY_ELEMENT          = "lobbyElement";
    public static final String EVENT_LADDER_ELEMENT         = "ladderElement";

    //Events in home
    public static final String EVENT_GO_RANKING             = "goRanking";
}
