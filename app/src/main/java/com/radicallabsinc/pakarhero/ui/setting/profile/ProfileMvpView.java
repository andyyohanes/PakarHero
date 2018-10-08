package com.radicallabsinc.pakarhero.ui.setting.profile;

import com.radicallabsinc.pakarhero.data.network.model.response.LoginResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface ProfileMvpView extends MvpView {
    void getProfile(LoginResponse.LoginData loginData);

    void onClickSave();

    void goToMainActivity();
}
