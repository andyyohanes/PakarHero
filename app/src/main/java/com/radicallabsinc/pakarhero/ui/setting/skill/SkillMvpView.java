package com.radicallabsinc.pakarhero.ui.setting.skill;

import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.LovResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface SkillMvpView extends MvpView {
    void getSkill(List<SkillResponse.SkillData> skillList);

    void onSuccessDeleteItem(int position);

    void showDialog();

    void onSuccessSaveItem();

    void getExpertiseData(List<ExpertiseResponse.ExpertiseData> expertiseList);

    void getLovData(List<LovResponse.LovData> lovList);

    void goToMainActivity();
}
