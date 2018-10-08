package com.radicallabsinc.pakarhero.ui.setting.workhistory;

import com.radicallabsinc.pakarhero.data.network.model.response.WorkHistoryResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface WorkHistoryMvpPresenter<V extends WorkHistoryMvpView> extends MvpPresenter<V> {
    void onViewPrepared();

    void deleteItem(WorkHistoryResponse.WorkHistoryData data, int position);

    void saveItem(String company, String period, String desc);
}
