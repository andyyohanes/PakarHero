package com.radicallabsinc.pakarhero.ui.setting.skill;

import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface SkillMvpPresenter<V extends SkillMvpView> extends MvpPresenter<V> {
    void onViewPrepared();

    void deleteItem(SkillResponse.SkillData data, int position);

    void saveItem(String expertiseCode, String currency, Integer pricePerSession, Integer sessionLength, String sessionUnit, String imageSource, String imageType);
}
