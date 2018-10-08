package com.radicallabsinc.pakarhero.data.network;

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
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<CheckNameResponse> checkUsername(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CHECK_USERNAME)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(CheckNameResponse.class);
    }

    @Override
    public Observable<ExpertiseResponse> getExpertise(ExpertiseRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_EXPERTISE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(ExpertiseResponse.class);
    }

    @Override
    public Observable<LovResponse> getLov(ExpertiseRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_LOV)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(LovResponse.class);
    }

    @Override
    public Observable<ExpertResponse> getExperts(ExpertRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_EXPERTS)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(ExpertResponse.class);
    }

    @Override
    public Observable<ReviewResponse> getReviews(ReviewRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_REVIEWS)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(ReviewResponse.class);
    }

    @Override
    public Observable<LoginResponse> login(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<BaseResponse> forgotPwd(JoinRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.FORGOT_PASSWORD)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> logout(LogoutRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGOUT)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> join(JoinRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.JOIN)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> verify(VerifyRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.VERIFY)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> newCode(VerifyRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.NEW_CODE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<LoginResponse> getProfile(LogoutRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_PROFILE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<BaseResponse> saveProfile(ProfileRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.EDIT_PROFILE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<CertificationResponse> getCerts(CertificationRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_CERTIFICATION)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(CertificationResponse.class);
    }

    @Override
    public Observable<CertificationResponse> saveCert(CertificationRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SAVE_CERTIFICATION)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(CertificationResponse.class);
    }

    @Override
    public Observable<CertificationResponse> delCert(CertificationRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.DELETE_CERTIFICATION)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(CertificationResponse.class);
    }

    @Override
    public Observable<SkillResponse> getSkill(SkillRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_SKILL)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(SkillResponse.class);
    }

    @Override
    public Observable<SkillResponse> saveSkill(SkillRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SAVE_SKILL)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(SkillResponse.class);
    }

    @Override
    public Observable<SkillResponse> delSkill(SkillRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.DELETE_SKILL)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(SkillResponse.class);
    }

    @Override
    public Observable<WorkHistoryResponse> getWorkHistory(WorkHistoryRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_WORK_HISTORY)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(WorkHistoryResponse.class);
    }

    @Override
    public Observable<WorkHistoryResponse> saveWorkHistory(WorkHistoryRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SAVE_WORK_HISTORY)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(WorkHistoryResponse.class);
    }

    @Override
    public Observable<WorkHistoryResponse> delWorkHistory(WorkHistoryRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.DELETE_WORK_HISTORY)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(WorkHistoryResponse.class);
    }

    @Override
    public Observable<CaseResponse> getCustomerCase(CustCaseRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_CUSTOMER_CASE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(CaseResponse.class);
    }

    @Override
    public Observable<CaseResponse> getExpertCase(ExpertCaseRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_EXPERT_CASE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(CaseResponse.class);
    }

    @Override
    public Observable<ChatHistResponse> getChatHistory(ChatHistRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CHAT_HIST)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(ChatHistResponse.class);
    }

    @Override
    public Observable<SendMsgResponse> sendMessage(SendMsgRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SEND_MSG)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(SendMsgResponse.class);
    }

    @Override
    public Observable<SendMsgResponse> uploadImage(SendMsgRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.UPLOAD_IMG)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(SendMsgResponse.class);
    }

    @Override
    public Observable<ChatHistResponse> getMessages(GetMessageRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.GET_MSGS)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(ChatHistResponse.class);
    }

    @Override
    public Observable<NotificationResponse> getNotification(NotificationRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.MSG_NOTIF)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(NotificationResponse.class);
    }

    @Override
    public Observable<ServiceFeeResponse> getServiceFee(ServiceFeeRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SERVICE_FEE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(ServiceFeeResponse.class);
    }

    @Override
    public Observable<BaseResponse> saveOrder(SaveOrderRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SAVE_ORDER)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<BaseResponse> pppdt(PayPalRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.PAYPAL_PDT)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<CustPayResponse> cpaybyorderid(CustPayRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CPAYBYORDERID)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(CustPayResponse.class);
    }

    @Override
    public Observable<BaseResponse> dokuMobile(DOKUMobileRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.DOKU_MOBILE)
                .setContentType(ApiEndPoint.CONTENT_TYPE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectObservable(BaseResponse.class);
    }
}
