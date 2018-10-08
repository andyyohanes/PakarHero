package com.radicallabsinc.pakarhero.ui.main.dashboard.my_case;

import android.support.v4.app.Fragment;

import com.radicallabsinc.pakarhero.data.network.model.response.CaseResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface MyCaseMvpView extends MvpView {

    void updateCase(List<CaseResponse.CaseData> activeCaseList, List<CaseResponse.CaseData> inactiveCaseList);

    void showFragment(Fragment fragment);
}
