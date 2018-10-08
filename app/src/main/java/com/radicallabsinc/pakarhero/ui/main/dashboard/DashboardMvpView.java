package com.radicallabsinc.pakarhero.ui.main.dashboard;

import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface DashboardMvpView extends MvpView {
    void changeTabLayout(int position);

    void refreshLayout();
}
