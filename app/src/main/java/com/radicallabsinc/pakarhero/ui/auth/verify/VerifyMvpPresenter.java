package com.radicallabsinc.pakarhero.ui.auth.verify;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface VerifyMvpPresenter<V extends VerifyMvpView> extends MvpPresenter<V> {
    void sendNewCode(String username, String phone);

    void verifyCode(String username, String countryCode, String phone, String code);
}
