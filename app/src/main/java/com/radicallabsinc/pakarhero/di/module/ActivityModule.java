package com.radicallabsinc.pakarhero.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.radicallabsinc.pakarhero.data.network.model.response.CaseDetailDataResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ChatHistResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ReviewResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.WorkHistoryResponse;
import com.radicallabsinc.pakarhero.di.ActivityContext;
import com.radicallabsinc.pakarhero.di.PerActivity;
import com.radicallabsinc.pakarhero.ui.auth.AuthMvpPresenter;
import com.radicallabsinc.pakarhero.ui.auth.AuthMvpView;
import com.radicallabsinc.pakarhero.ui.auth.AuthPresenter;
import com.radicallabsinc.pakarhero.ui.auth.join.JoinMvpPresenter;
import com.radicallabsinc.pakarhero.ui.auth.join.JoinMvpView;
import com.radicallabsinc.pakarhero.ui.auth.join.JoinPresenter;
import com.radicallabsinc.pakarhero.ui.auth.login.LoginMvpPresenter;
import com.radicallabsinc.pakarhero.ui.auth.login.LoginMvpView;
import com.radicallabsinc.pakarhero.ui.auth.login.LoginPresenter;
import com.radicallabsinc.pakarhero.ui.auth.verify.VerifyMvpPresenter;
import com.radicallabsinc.pakarhero.ui.auth.verify.VerifyMvpView;
import com.radicallabsinc.pakarhero.ui.auth.verify.VerifyPresenter;
import com.radicallabsinc.pakarhero.ui.chat.ChatAdapter;
import com.radicallabsinc.pakarhero.ui.chat.ChatMvpPresenter;
import com.radicallabsinc.pakarhero.ui.chat.ChatMvpView;
import com.radicallabsinc.pakarhero.ui.chat.ChatPresenter;
import com.radicallabsinc.pakarhero.ui.main.MainMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.MainMvpView;
import com.radicallabsinc.pakarhero.ui.main.MainPagerAdapterAuthorized;
import com.radicallabsinc.pakarhero.ui.main.MainPagerAdapterUnauthorized;
import com.radicallabsinc.pakarhero.ui.main.MainPresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardMvpView;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardPagerAdapter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardPresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail.CaseDetailAdapter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail.CaseDetailMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail.CaseDetailMvpView;
import com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail.CaseDetailPresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCaseAdapter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCaseMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCaseMvpView;
import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCasePresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCaseAdapter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCaseMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCaseMvpView;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCasePresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.ExpertiseAdapter;
import com.radicallabsinc.pakarhero.ui.main.expertise.ExpertiseMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.ExpertiseMvpView;
import com.radicallabsinc.pakarhero.ui.main.expertise.ExpertisePresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertAdapter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertMvpView;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertPresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.ExpertDetailCertificationAdapter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.ExpertDetailMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.ExpertDetailMvpView;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.ExpertDetailPresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.ExpertDetailWorkHistoryAdapter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review.ReviewAdapter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review.ReviewMvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review.ReviewMvpView;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review.ReviewPresenter;
import com.radicallabsinc.pakarhero.ui.setting.SettingMvpPresenter;
import com.radicallabsinc.pakarhero.ui.setting.SettingMvpView;
import com.radicallabsinc.pakarhero.ui.setting.SettingPresenter;
import com.radicallabsinc.pakarhero.ui.setting.certification.CertificationAdapter;
import com.radicallabsinc.pakarhero.ui.setting.certification.CertificationMvpPresenter;
import com.radicallabsinc.pakarhero.ui.setting.certification.CertificationMvpView;
import com.radicallabsinc.pakarhero.ui.setting.certification.CertificationPresenter;
import com.radicallabsinc.pakarhero.ui.setting.profile.ProfileMvpPresenter;
import com.radicallabsinc.pakarhero.ui.setting.profile.ProfileMvpView;
import com.radicallabsinc.pakarhero.ui.setting.profile.ProfilePresenter;
import com.radicallabsinc.pakarhero.ui.setting.skill.SkillAdapter;
import com.radicallabsinc.pakarhero.ui.setting.skill.SkillMvpPresenter;
import com.radicallabsinc.pakarhero.ui.setting.skill.SkillMvpView;
import com.radicallabsinc.pakarhero.ui.setting.skill.SkillPresenter;
import com.radicallabsinc.pakarhero.ui.setting.workhistory.WorkHistoryAdapter;
import com.radicallabsinc.pakarhero.ui.setting.workhistory.WorkHistoryMvpPresenter;
import com.radicallabsinc.pakarhero.ui.setting.workhistory.WorkHistoryMvpView;
import com.radicallabsinc.pakarhero.ui.setting.workhistory.WorkHistoryPresenter;
import com.radicallabsinc.pakarhero.utils.rx.AppSchedulerProvider;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AuthMvpPresenter<AuthMvpView> provideAuthPresenter(
            AuthPresenter<AuthMvpView> presenter) {
        return presenter;
    }

    @Provides
    MainPagerAdapterUnauthorized provideFeedPagerAdapter(AppCompatActivity activity) {
        return new MainPagerAdapterUnauthorized(activity.getSupportFragmentManager());
    }

    @Provides
    MainPagerAdapterAuthorized provideFeedPagerAdapterAuthorized(AppCompatActivity activity) {
        return new MainPagerAdapterAuthorized(activity.getSupportFragmentManager());
    }

    @Provides
    ExpertiseMvpPresenter<ExpertiseMvpView> provideExpertisePresenter(ExpertisePresenter<ExpertiseMvpView> presenter){
        return presenter;
    }

    @Provides
    ExpertiseAdapter provideExpertiseAdapter() {
        return new ExpertiseAdapter(new ArrayList<ExpertiseResponse.ExpertiseData>());
    }

    @Provides
    ExpertMvpPresenter<ExpertMvpView> provideExpertPresenter(ExpertPresenter<ExpertMvpView> presenter){
        return presenter;
    }

    @Provides
    ExpertAdapter provideExpertAdapter() {
        return new ExpertAdapter(new ArrayList<ExpertResponse.ExpertData>());
    }

    @Provides
    ExpertDetailMvpPresenter<ExpertDetailMvpView> provideExpertDetailPresenter(ExpertDetailPresenter<ExpertDetailMvpView> presenter){
        return presenter;
    }

    @Provides
    ExpertDetailCertificationAdapter provideExpertDetailCertificationAdapter() {
        return new ExpertDetailCertificationAdapter(new ArrayList<CertificationResponse.CertificationData>());
    }

    @Provides
    ExpertDetailWorkHistoryAdapter provideExpertDetailWorkHistoryAdapter() {
        return new ExpertDetailWorkHistoryAdapter(new ArrayList<WorkHistoryResponse.WorkHistoryData>());
    }

    @Provides
    ReviewAdapter provideReviewAdapter() {
        return new ReviewAdapter(new ArrayList<ReviewResponse.ReviewData>());
    }

    @Provides
    ReviewMvpPresenter<ReviewMvpView> provideReviewPresenter(ReviewPresenter<ReviewMvpView> presenter){
        return presenter;
    }

    @Provides
    CustomerCaseAdapter provideCustomerCaseAdapter() {
        return new CustomerCaseAdapter(new ArrayList<CaseResponse.CaseData>());
    }

    @Provides
    CustomerCaseMvpPresenter<CustomerCaseMvpView> provideCustomerCasePresenter(CustomerCasePresenter<CustomerCaseMvpView> presenter){
        return presenter;
    }

    @Provides
    MyCaseAdapter provideMyCaseAdapter() {
        return new MyCaseAdapter(new ArrayList<CaseResponse.CaseData>());
    }

    @Provides
    MyCaseMvpPresenter<MyCaseMvpView> provideMyCasePresenter(MyCasePresenter<MyCaseMvpView> presenter){
        return presenter;
    }

    @Provides
    ChatAdapter provideChatAdapter() {
        return new ChatAdapter(new ArrayList<ChatHistResponse.ChatData>());
    }

    @Provides
    @PerActivity
    ChatMvpPresenter<ChatMvpView> provideChatPresenter(ChatPresenter<ChatMvpView> presenter){
        return presenter;
    }

    @Provides
    CaseDetailAdapter provideCaseDetailAdapter() {
        return new CaseDetailAdapter(new ArrayList<CaseDetailDataResponse>());
    }

    @Provides
    CaseDetailMvpPresenter<CaseDetailMvpView> provideCaseDetailPresenter(CaseDetailPresenter<CaseDetailMvpView> presenter){
        return presenter;
    }

    @Provides
    DashboardPagerAdapter provideDashboardPagerAdapter(AppCompatActivity activity) {
        return new DashboardPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    @PerActivity
    DashboardMvpPresenter<DashboardMvpView> provideDashboardPresenter(
            DashboardPresenter<DashboardMvpView> presenter) {
        return presenter;
    }

    @Provides
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter){
        return presenter;
    }

    @Provides
    JoinMvpPresenter<JoinMvpView> provideJoinPresenter(JoinPresenter<JoinMvpView> presenter){
        return presenter;
    }

    @Provides
    VerifyMvpPresenter<VerifyMvpView> provideVerifyPresenter(VerifyPresenter<VerifyMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    SettingMvpPresenter<SettingMvpView> provideSettingPresenter(
            SettingPresenter<SettingMvpView> presenter) {
        return presenter;
    }

    @Provides
    ProfileMvpPresenter<ProfileMvpView> provideProfilePresenter(ProfilePresenter<ProfileMvpView> presenter){
        return presenter;
    }

    @Provides
    CertificationMvpPresenter<CertificationMvpView> provideCertificationPresenter(CertificationPresenter<CertificationMvpView> presenter){
        return presenter;
    }

    @Provides
    CertificationAdapter provideCertificationAdapter() {
        return new CertificationAdapter(new ArrayList<CertificationResponse.CertificationData>());
    }

    @Provides
    SkillMvpPresenter<SkillMvpView> provideSkillPresenter(SkillPresenter<SkillMvpView> presenter){
        return presenter;
    }

    @Provides
    SkillAdapter provideSkillAdapter() {
        return new SkillAdapter(new ArrayList<SkillResponse.SkillData>());
    }

    @Provides
    WorkHistoryMvpPresenter<WorkHistoryMvpView> provideWorkHistoryPresenter(WorkHistoryPresenter<WorkHistoryMvpView> presenter){
        return presenter;
    }

    @Provides
    WorkHistoryAdapter provideWorkHistoryAdapter() {
        return new WorkHistoryAdapter(new ArrayList<WorkHistoryResponse.WorkHistoryData>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    GridLayoutManager provideGridLayoutManager(AppCompatActivity activity, int spanCount) {
        return new GridLayoutManager(activity, spanCount);
    }
}
