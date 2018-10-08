package com.radicallabsinc.pakarhero.ui.auth.verify;

import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface VerifyMvpView extends MvpView {
    void sendNewCodeClicked();

    void verifyClicked();
}
