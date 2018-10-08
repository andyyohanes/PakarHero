package com.radicallabsinc.pakarhero.ui.auth.join;

import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface JoinMvpView extends MvpView {

    boolean checkTerms();

    void onTermsClicked();

    void showSuccessDialog(String username,String countryCode, String phone);
}
