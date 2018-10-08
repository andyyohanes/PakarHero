package com.radicallabsinc.pakarhero.ui.main.expertise.expert;

import android.support.v4.app.Fragment;

import com.radicallabsinc.pakarhero.data.network.model.response.ExpertResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface ExpertMvpView extends MvpView {

    void updateExpert(List<ExpertResponse.ExpertData> expertList);

    void showNotFound();

    void showFragment(Fragment fragment);
}
