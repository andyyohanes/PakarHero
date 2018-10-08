package com.radicallabsinc.pakarhero.ui.setting.certification;

import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface CertificationMvpView extends MvpView {
    void getCertification(List<CertificationResponse.CertificationData> certificationList);

    void onSuccessDeleteItem(int position);

    void showDialog();

    void onSuccessSaveItem();

    void goToMainActivity();
}
