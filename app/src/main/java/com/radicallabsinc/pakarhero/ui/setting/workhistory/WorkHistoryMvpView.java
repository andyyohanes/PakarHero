package com.radicallabsinc.pakarhero.ui.setting.workhistory;

import com.radicallabsinc.pakarhero.data.network.model.response.WorkHistoryResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface WorkHistoryMvpView extends MvpView {
    void getWorkHistory(List<WorkHistoryResponse.WorkHistoryData> workHistoryList);

    void onSuccessDeleteItem(int position);

    void showDialog();

    void onSuccessSaveItem();

    void goToMainActivity();
}
