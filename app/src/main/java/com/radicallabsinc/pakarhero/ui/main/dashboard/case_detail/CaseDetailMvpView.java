package com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface CaseDetailMvpView extends MvpView {
    void processPayment();

    PayPalPayment getPaymentforPayPal();

    void onSuccessSaveOrder(String orderId);

    void startPayPalPaymentActivity(PayPalPayment thingsToBuy, PayPalConfiguration config, int requestCode);

    void startPayPalService(PayPalConfiguration configuration);

    void refreshLayout();

//    void showDokuDialog();
}
