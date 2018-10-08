package com.radicallabsinc.pakarhero.ui.setting.profile;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface ProfileMvpPresenter<V extends ProfileMvpView> extends MvpPresenter<V> {
    void onViewPrepared();

    void saveProfile(String firstName, String lastName, String countryCode, String phone, String desc, String languages, String paypalEmail, String dokuBankActName, String dokuBankActNumber, String dokuBankName, String dokuBranch, String img, String imgType);
}
