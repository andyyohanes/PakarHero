package com.radicallabsinc.pakarhero.ui.main.expertise;

import android.support.v4.app.Fragment;

import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface ExpertiseMvpView extends MvpView {

    void updateExpertise(List<ExpertiseResponse.ExpertiseData> expertiseList);

    void showFragment(Fragment fragment);
}
