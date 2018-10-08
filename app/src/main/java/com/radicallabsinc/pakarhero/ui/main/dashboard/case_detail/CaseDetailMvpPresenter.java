package com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail;

import com.doku.sdkocov2.model.PaymentItems;
import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

import org.json.JSONObject;

public interface CaseDetailMvpPresenter<V extends CaseDetailMvpView> extends MvpPresenter<V> {
    void saveOrder(String caseId, String orderId, double serviceFee, double amount, String currency, String payMethod);

    void sendPayPalResponseData(String orderId, String payPalResponseData);

    void performDOKUPayment(int menuPaymentChannel, String orderId, String amount, String currency);

    PaymentItems getDOKUPaymentItem();

    void completeDOKUPayment(JSONObject responseJson, String response);

    void sendDOKUResponseData();
}
