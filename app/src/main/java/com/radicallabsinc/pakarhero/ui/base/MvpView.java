package com.radicallabsinc.pakarhero.ui.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;

public interface MvpView {
    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    Context getViewContext();
}
