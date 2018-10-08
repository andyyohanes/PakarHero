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

import io.reactivex.Observable;

public interface ApiHelper {
    Observable<CheckNameResponse> checkUsername(LoginRequest request);

    Observable<ExpertiseResponse> getExpertise(ExpertiseRequest request);

    Observable<LovResponse> getLov(ExpertiseRequest request);

    Observable<ExpertResponse> getExperts(ExpertRequest request);

    Observable<ReviewResponse> getReviews(ReviewRequest request);

    Observable<LoginResponse> login(LoginRequest request);

    Observable<BaseResponse> forgotPwd(JoinRequest request);

    Observable<BaseResponse> logout(LogoutRequest request);

    Observable<BaseResponse> join(JoinRequest request);

    Observable<BaseResponse> verify(VerifyRequest request);

    Observable<BaseResponse> newCode(VerifyRequest request);

    Observable<LoginResponse> getProfile(LogoutRequest request);

    Observable<BaseResponse> saveProfile(ProfileRequest request);

    Observable<CertificationResponse> getCerts(CertificationRequest request);

    Observable<CertificationResponse> saveCert(CertificationRequest request);

    Observable<CertificationResponse> delCert(CertificationRequest request);

    Observable<SkillResponse> getSkill(SkillRequest request);

    Observable<SkillResponse> saveSkill(SkillRequest request);

    Observable<SkillResponse> delSkill(SkillRequest request);

    Observable<WorkHistoryResponse> getWorkHistory(WorkHistoryRequest request);

    Observable<WorkHistoryResponse> saveWorkHistory(WorkHistoryRequest request);

    Observable<WorkHistoryResponse> delWorkHistory(WorkHistoryRequest request);

    Observable<CaseResponse> getCustomerCase(CustCaseRequest request);

    Observable<CaseResponse> getExpertCase(ExpertCaseRequest request);

    Observable<ChatHistResponse> getChatHistory(ChatHistRequest request);

    Observable<SendMsgResponse> sendMessage(SendMsgRequest request);

    Observable<SendMsgResponse> uploadImage(SendMsgRequest request);

    Observable<ChatHistResponse> getMessages(GetMessageRequest request);

    Observable<NotificationResponse> getNotification(NotificationRequest request);

    Observable<ServiceFeeResponse> getServiceFee(ServiceFeeRequest request);

    Observable<BaseResponse> saveOrder(SaveOrderRequest request);

    Observable<BaseResponse> pppdt(PayPalRequest request);

    Observable<CustPayResponse> cpaybyorderid(CustPayRequest request);

    Observable<BaseResponse> dokuMobile(DOKUMobileRequest request);
}
