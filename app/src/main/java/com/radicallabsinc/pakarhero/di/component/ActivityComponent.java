package com.radicallabsinc.pakarhero.di.component;

import com.radicallabsinc.pakarhero.ui.auth.AuthActivity;
import com.radicallabsinc.pakarhero.ui.auth.join.JoinFragment;
import com.radicallabsinc.pakarhero.ui.auth.login.LoginFragment;
import com.radicallabsinc.pakarhero.ui.auth.verify.VerifyFragment;
import com.radicallabsinc.pakarhero.ui.chat.ChatActivity;
import com.radicallabsinc.pakarhero.ui.main.MainActivity;
import com.radicallabsinc.pakarhero.di.PerActivity;
import com.radicallabsinc.pakarhero.di.module.ActivityModule;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardActivity;
import com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail.CaseDetailFragment;
import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCaseFragment;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCaseFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.ExpertiseFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.ExpertDetailFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review.ReviewActivity;
import com.radicallabsinc.pakarhero.ui.setting.SettingActivity;
import com.radicallabsinc.pakarhero.ui.setting.certification.CertificationFragment;
import com.radicallabsinc.pakarhero.ui.setting.profile.ProfileFragment;
import com.radicallabsinc.pakarhero.ui.setting.skill.SkillFragment;
import com.radicallabsinc.pakarhero.ui.setting.workhistory.WorkHistoryFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(ExpertiseFragment fragment);

    void inject(ExpertFragment fragment);

    void inject(ExpertDetailFragment fragment);

    void inject(ReviewActivity activity);

    void inject(DashboardActivity activity);

    void inject(CustomerCaseFragment fragment);

    void inject(MyCaseFragment fragment);

    void inject(CaseDetailFragment fragment);

    void inject(ChatActivity activity);

    void inject(AuthActivity activity);

    void inject(LoginFragment fragment);

    void inject(JoinFragment fragment);

    void inject(VerifyFragment fragment);

    void inject(SettingActivity activity);

    void inject(ProfileFragment fragment);

    void inject(CertificationFragment fragment);

    void inject(SkillFragment fragment);

    void inject(WorkHistoryFragment fragment);

}
