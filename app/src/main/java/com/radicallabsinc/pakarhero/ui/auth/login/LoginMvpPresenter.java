package com.radicallabsinc.pakarhero.ui.auth.login;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onActionClicked();

    void checkLogin(String username, String password);

    void doLogin(Long userId, String password, String pushToken, String userName, String authToken, String userType, String userLocale, String userPic);

    void onUsernameChanged(String string);

    void forgotPwd(String username, String userLocale);
}
