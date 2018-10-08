package com.radicallabsinc.pakarhero.ui.auth.join;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface JoinMvpPresenter<V extends JoinMvpView> extends MvpPresenter<V> {

    void onJoinClicked(String username, String password, String countryCode, String phone, String userLocale);
}
