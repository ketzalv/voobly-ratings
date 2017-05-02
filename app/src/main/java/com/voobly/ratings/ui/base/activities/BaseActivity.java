package com.voobly.ratings.ui.base.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.voobly.ratings.R;
import com.voobly.ratings.VooblyApp;
import com.voobly.ratings.data.DataManagerEvent;
import com.voobly.ratings.data.local.Preferencias;
import com.voobly.ratings.ui.base.interfaces.IEventOnFragment;

import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_DISMISS_DIALOG;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_DIALOG;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_ERROR;
import static com.voobly.ratings.ui.base.BaseEventsContract.EVENT_SHOW_MESSAGE;
import static com.voobly.ratings.utils.Utils.showSnackBar;
import static com.voobly.ratings.utils.Utils.showToast;

/**
 * @author Jose Alberto Vazquez
 * @email imketzal@gmail.com
 * @version 1.0
 * Created by jvazquez on 02/05/2017.
 */

public  abstract class BaseActivity extends AppCompatActivity implements IEventOnFragment {
    protected FragmentTransaction fragmentTransaction;
    protected FragmentManager fragmentManager;
    @IdRes
    protected int idContainerFragments;
    @IdRes
    protected int idCoordinatorLayout;
    @IdRes
    protected int idProgress;

    protected Preferencias pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentManager = getSupportFragmentManager();
        pref = VooblyApp.getInstance().getPref();
        idContainerFragments = -1;
        idCoordinatorLayout = -1;
        idProgress = -1;
        setTitle("");
    }

    public void setIdsContainers(@IdRes int id_coordinatorLayout, @IdRes int id_containerFragments, @IdRes int id_progress){
        this.idCoordinatorLayout = id_coordinatorLayout;
        this.idContainerFragments = id_containerFragments;
        this.idProgress = id_progress;

    }
    public enum DIRECTION {
        FORDWARD(R.anim.slide_from_right, R.anim.slide_to_left),
        BACK(R.anim.slide_from_left, R.anim.slide_to_right),
        NONE(0, 0);

        private int enterAnimation;
        private int exitAnimation;

        private DIRECTION(@AnimRes int enterAnimation, @AnimRes int exitAnimation) {
            this.enterAnimation = enterAnimation;
            this.exitAnimation = exitAnimation;
        }

        public int getEnterAnimation() {
            return enterAnimation;
        }

        public int getExitAnimation() {
            return exitAnimation;
        }
    }

    protected void loadFragment(@NonNull Fragment fragment) {
        loadFragment(fragment, idContainerFragments, DIRECTION.NONE, false);
    }

    protected void loadFragment(@NonNull Fragment fragment, @IdRes int idContainer) {
        loadFragment(fragment, idContainer, DIRECTION.NONE, false);
    }

    protected void loadFragment(@NonNull Fragment fragment, boolean addToBackStack) {
        loadFragment(fragment, idContainerFragments, DIRECTION.NONE, addToBackStack);
    }

    protected void loadFragment(@NonNull Fragment fragment, @IdRes int idContainer, boolean addToBackStack) {
        loadFragment(fragment, idContainer, DIRECTION.NONE, addToBackStack);
    }

    protected void loadFragment(@NonNull Fragment fragment, @NonNull DIRECTION direction) {
        loadFragment(fragment, idContainerFragments, direction, false);
    }

    protected void loadFragment(@NonNull Fragment fragment, @IdRes int idContainer, @NonNull DIRECTION direction) {
        loadFragment(fragment, idContainer, direction, false);
    }

    protected void loadFragment(@NonNull Fragment fragment, @NonNull DIRECTION direction,
                                boolean addToBackStack) {
        loadFragment(fragment, idContainerFragments, direction, addToBackStack);
    }

    protected void loadFragment(@NonNull Fragment fragment, @IdRes int idContainer, @NonNull DIRECTION direction,
                                boolean addToBackStack) {
        this.idContainerFragments = idContainer;
        fragmentTransaction = fragmentManager.beginTransaction();
        if (!direction.equals(DIRECTION.NONE)) {
            fragmentTransaction.setCustomAnimations(direction.getEnterAnimation(), direction.getExitAnimation());
        }
        fragmentTransaction.replace(idContainer, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }


    protected Fragment getCurrentFragment() {
        if (idContainerFragments != -1) {
            return getCurrentFragment(idContainerFragments);
        }
        return null;
    }

    protected Fragment getCurrentFragment(@IdRes int idContainer) {
        return fragmentManager.findFragmentById(idContainer);
    }
    protected View getProgressView(){
        if(idProgress != -1){
            return findViewById(idProgress);
        }
        return null;
    }
    protected CoordinatorLayout getCoordinatorLayout(){
        if(idCoordinatorLayout != -1){
            return (CoordinatorLayout) findViewById(idCoordinatorLayout);
        }
        return null;
    }
    protected void showProgress(String message) {
        View view = findViewById(idProgress);
        if (view != null) {
            TextView tv_message = (TextView) findViewById(R.id.progressText);
            view.setVisibility(View.VISIBLE);
            if (tv_message != null && message != null && !message.isEmpty()) {
                tv_message.setText(message);
            }
        }
    }
    protected boolean isShownProgress() {
        return findViewById(idProgress) != null && findViewById(idProgress).isShown() && findViewById(idProgress).getVisibility() == View.VISIBLE;
    }
    protected void hideProgress() {
        View view = findViewById(idProgress);
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }
    protected void showMessage(String message) {
        View rootCoordinator = findViewById(idCoordinatorLayout);
        if (rootCoordinator != null) {
            showSnackBar(rootCoordinator, message);
        } else {
            showToast(message, this);
        }
    }
    protected void showMessageWithAction(String message, String actionName, int duration, View.OnClickListener listener) {
        View rootCoordinator = findViewById(idCoordinatorLayout);
        if (rootCoordinator != null) {
            showSnackBar(rootCoordinator, message, actionName, duration, listener);
        } else {
            showToast(message, this);
        }
    }

    @Override
    public void onEvent(DataManagerEvent data) {
        switch (data.getEvent()){
            case EVENT_SHOW_DIALOG:
                if (data.getData() != null && data.getData() instanceof String) {
                    showProgress((String) data.getData());
                }
                break;
            case EVENT_DISMISS_DIALOG:
                hideProgress();
                break;
            case EVENT_SHOW_ERROR:
                if (data.getData() != null && data.getData() instanceof String) {
                    showMessage((String) data.getData());
                }
                break;
            case EVENT_SHOW_MESSAGE:
                if (data.getData() != null && data.getData() instanceof String) {
                    showMessage((String) data.getData());
                }
                break;
            default:
                break;
        }
    }
}
