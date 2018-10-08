package com.radicallabsinc.pakarhero.data;

import android.content.Context;

import com.radicallabsinc.pakarhero.data.network.ApiHelper;
import com.radicallabsinc.pakarhero.data.network.model.request.CertificationRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ChatHistRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.CustCaseRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.CustPayRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.DOKUMobileRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertCaseRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.GetMessageRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.JoinRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.LoginRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.LogoutRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.NotificationRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.PayPalRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ProfileRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ReviewRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.SaveOrderRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.SendMsgRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ServiceFeeRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.SkillRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.VerifyRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.WorkHistoryRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.BaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ChatHistResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CheckNameResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CustPayResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertiseRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.LoginResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.LovResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.NotificationResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ReviewResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SendMsgResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ServiceFeeResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.WorkHistoryResponse;
import com.radicallabsinc.pakarhero.data.prefs.PreferencesHelper;
import com.radicallabsinc.pakarhero.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {
    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserPassword() {
        return mPreferencesHelper.getCurrentUserPassword();
    }

    @Override
    public void setCurrentUserPassword(String password) {
        mPreferencesHelper.setCurrentUserPassword(password);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public String getCurrentUserType() {
        return mPreferencesHelper.getCurrentUserType();
    }

    @Override
    public void setCurrentUserType(String userType) {
        mPreferencesHelper.setCurrentUserType(userType);
    }

    @Override
    public String getCurrentUserLocale() {
        return mPreferencesHelper.getCurrentUserLocale();
    }

    @Override
    public void setCurrentUserLocale(String userLocale) {
        mPreferencesHelper.setCurrentUserLocale(userLocale);
    }

    @Override
    public String getCurrentGCMToken() {
        return mPreferencesHelper.getCurrentGCMToken();
    }

    @Override
    public void setCurrentGCMToken(String gcmToken) {
        mPreferencesHelper.setCurrentGCMToken(gcmToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId,
            String password,
            String gcmToken,
            LoggedInMode loggedInMode,
            String userName,
            String userType,
            String userLocale,
            String profilePicPath) {

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserPassword(password);
        setCurrentGCMToken(gcmToken);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserType(userType);
        setCurrentUserLocale(userLocale);
        setCurrentUserProfilePicUrl(profilePicPath);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null,
                null);
    }

    @Override
    public Observable<CheckNameResponse> checkUsername(LoginRequest request) {
        return mApiHelper.checkUsername(request);
    }

    @Override
    public Observable<ExpertiseResponse> getExpertise(ExpertiseRequest request) {
        return mApiHelper.getExpertise(request);
    }

    @Override
    public Observable<LovResponse> getLov(ExpertiseRequest request) {
        return mApiHelper.getLov(request);
    }

    @Override
    public Observable<ExpertResponse> getExperts(ExpertRequest request) {
        return mApiHelper.getExperts(request);
    }

    @Override
    public Observable<ReviewResponse> getReviews(ReviewRequest request) {
        return mApiHelper.getReviews(request);
    }

    @Override
    public Observable<LoginResponse> login(LoginRequest request) {
        return mApiHelper.login(request);
    }

    @Override
    public Observable<BaseResponse> forgotPwd(JoinRequest request) {
        return mApiHelper.forgotPwd(request);
    }

    @Override
    public Observable<BaseResponse> logout(LogoutRequest request) {
        return mApiHelper.logout(request);
    }

    @Override
    public Observable<BaseResponse> join(JoinRequest request) {
        return mApiHelper.join(request);
    }

    @Override
    public Observable<BaseResponse> verify(VerifyRequest request) {
        return mApiHelper.verify(request);
    }

    @Override
    public Observable<BaseResponse> newCode(VerifyRequest request) {
        return mApiHelper.newCode(request);
    }

    @Override
    public Observable<LoginResponse> getProfile(LogoutRequest request) {
        return mApiHelper.getProfile(request);
    }

    @Override
    public Observable<BaseResponse> saveProfile(ProfileRequest request) {
        return mApiHelper.saveProfile(request);
    }

    @Override
    public Observable<CertificationResponse> getCerts(CertificationRequest request) {
        return mApiHelper.getCerts(request);
    }

    @Override
    public Observable<CertificationResponse> saveCert(CertificationRequest request) {
        return mApiHelper.saveCert(request);
    }

    @Override
    public Observable<CertificationResponse> delCert(CertificationRequest request) {
        return mApiHelper.delCert(request);
    }

    @Override
    public Observable<SkillResponse> getSkill(SkillRequest request) {
        return mApiHelper.getSkill(request);
    }

    @Override
    public Observable<SkillResponse> saveSkill(SkillRequest request) {
        return mApiHelper.saveSkill(request);
    }

    @Override
    public Observable<SkillResponse> delSkill(SkillRequest request) {
        return mApiHelper.delSkill(request);
    }

    @Override
    public Observable<WorkHistoryResponse> getWorkHistory(WorkHistoryRequest request) {
        return mApiHelper.getWorkHistory(request);
    }

    @Override
    public Observable<WorkHistoryResponse> saveWorkHistory(WorkHistoryRequest request) {
        return mApiHelper.saveWorkHistory(request);
    }

    @Override
    public Observable<WorkHistoryResponse> delWorkHistory(WorkHistoryRequest request) {
        return mApiHelper.delWorkHistory(request);
    }

    @Override
    public Observable<CaseResponse> getCustomerCase(CustCaseRequest request) {
        return mApiHelper.getCustomerCase(request);
    }

    @Override
    public Observable<CaseResponse> getExpertCase(ExpertCaseRequest request) {
        return mApiHelper.getExpertCase(request);
    }

    @Override
    public Observable<ChatHistResponse> getChatHistory(ChatHistRequest request) {
        return mApiHelper.getChatHistory(request);
    }

    @Override
    public Observable<SendMsgResponse> sendMessage(SendMsgRequest request) {
        return mApiHelper.sendMessage(request);
    }

    @Override
    public Observable<SendMsgResponse> uploadImage(SendMsgRequest request) {
        return mApiHelper.uploadImage(request);
    }

    @Override
    public Observable<ChatHistResponse> getMessages(GetMessageRequest request) {
        return mApiHelper.getMessages(request);
    }

    @Override
    public Observable<NotificationResponse> getNotification(NotificationRequest request) {
        return mApiHelper.getNotification(request);
    }

    @Override
    public Observable<ServiceFeeResponse> getServiceFee(ServiceFeeRequest request) {
        return mApiHelper.getServiceFee(request);
    }

    @Override
    public Observable<BaseResponse> saveOrder(SaveOrderRequest request) {
        return mApiHelper.saveOrder(request);
    }

    @Override
    public Observable<BaseResponse> pppdt(PayPalRequest request) {
        return mApiHelper.pppdt(request);
    }

    @Override
    public Observable<CustPayResponse> cpaybyorderid(CustPayRequest request) {
        return mApiHelper.cpaybyorderid(request);
    }

    @Override
    public Observable<BaseResponse> dokuMobile(DOKUMobileRequest request) {
        return mApiHelper.dokuMobile(request);
    }
}
