package com.radicallabsinc.pakarhero.ui.auth.login;

import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface LoginMvpView extends MvpView {

    boolean isPasswordVisible();

    void changePasswordLayoutVisibility(boolean isVisible);
    void setFocusToPassword();
    void changeTitle();
    void showDialogForgotPwd();
    void showSuccessDialog();
    void hideJoin();
    void showJoin();
    void onErrorShow(String message);

    String getUsername();
    String getPassword();
    String getPushToken();

    void goToMainActivity();
    void onJoinClicked(String username);
}
