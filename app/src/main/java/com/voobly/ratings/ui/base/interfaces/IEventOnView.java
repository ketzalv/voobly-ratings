package com.voobly.ratings.ui.base.interfaces;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public interface IEventOnView {
    void showProgress(String message);
    void hideProgress();
    void showMessage(String Message);
}
