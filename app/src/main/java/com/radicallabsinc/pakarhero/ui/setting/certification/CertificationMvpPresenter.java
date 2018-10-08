package com.radicallabsinc.pakarhero.ui.setting.certification;

import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface CertificationMvpPresenter<V extends CertificationMvpView> extends MvpPresenter<V> {
    void onViewPrepared();

    void deleteItem(CertificationResponse.CertificationData data, int position);

    void saveItem(String period, String desc);
}
