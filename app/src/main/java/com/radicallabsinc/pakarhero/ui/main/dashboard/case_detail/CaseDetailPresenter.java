package com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.doku.sdkocov2.DirectSDK;
import com.doku.sdkocov2.interfaces.iPaymentCallback;
import com.doku.sdkocov2.model.PaymentItems;
import com.radicallabsinc.pakarhero.BuildConfig;
import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.CustPayRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.DOKUMobileRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.DOKUPaymentRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.PayPalRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.SaveOrderRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.BaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CustPayResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.PaymentUtils;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class CaseDetailPresenter<V extends CaseDetailMvpView> extends BasePresenter<V> implements CaseDetailMvpPresenter<V> {

    private DirectSDK DOKUDirect;
    private String invoiceNumber, amount, currency;

    @Inject
    public CaseDetailPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void saveOrder(String caseId, final String orderId, double serviceFee, double amount, String currency, String payMethod) {
        getMvpView().showLoading();
        SaveOrderRequest saveOrderRequest = new SaveOrderRequest(getDataManager().getCurrentUserId(),getDataManager().getAccessToken(),caseId,orderId,currency,amount,serviceFee,payMethod);
        getCompositeDisposable().add(getDataManager()
                .saveOrder(saveOrderRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        if (baseResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().onSuccessSaveOrder(orderId);
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void sendPayPalResponseData(String orderId, String payPalResponseData) {
        getMvpView().showLoading();
        PayPalRequest payPalRequest = new PayPalRequest(getDataManager().getCurrentUserId(),getDataManager().getAccessToken(),orderId,payPalResponseData);
        getCompositeDisposable().add(getDataManager()
                .pppdt(payPalRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        Log.e("response",baseResponse.getStatus());
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void performDOKUPayment(int menuPaymentChannel, String orderId, String amount, String currency) {
        invoiceNumber = orderId;
        this.amount = String.format(java.util.Locale.US, "%.2f", Double.parseDouble(amount));
        this.currency = "360";
        DOKUDirect = new DirectSDK();
        Log.e("masuk performD","iya");

        PaymentItems item = getDOKUPaymentItem();
        DOKUDirect.setCart_details(item);
        DOKUDirect.setPaymentChannel(menuPaymentChannel);
        DOKUDirect.getResponse(new iPaymentCallback() {
            @Override
            public void onSuccess(String text) {
                Log.e("masuk success performD","iya");
                Log.d("RESPONSE", text);
                try {
                    JSONObject response = new JSONObject(text);
                    completeDOKUPayment(response,text);
                } catch (JSONException e) {
                    e.printStackTrace();
                    completeDOKUPayment(null,null);
                }
            }

            @Override
            public void onError(String s) {
                Log.e("masuk error performD",s);
                completeDOKUPayment(null,null);
            }

            @Override
            public void onException(Exception e) {
                Log.e("masuk exception","iya");
                e.printStackTrace();
            }
        }, getMvpView().getViewContext());
    }

    @Override
    public PaymentItems getDOKUPaymentItem() {

        String merchantCode = getMvpView().getViewContext().getResources().getString(R.string.doku_merchant_code);
        String sharedKey = getMvpView().getViewContext().getResources().getString(R.string.doku_shared_key);

        PaymentItems paymentItems = new PaymentItems();
        paymentItems.setDataAmount(amount);
        Log.e("amount",paymentItems.getDataAmount());
        paymentItems.setDataBasket("[{\"name\":\"Payment for " + invoiceNumber + "\",\"amount\":\"" + amount + "\",\"quantity\":\"1\",\"subtotal\":\"" + amount + "\"}]");
        Log.e("dataBasket",paymentItems.getDataBasket());
        paymentItems.setDataCurrency(currency);
        Log.e("currency",paymentItems.getDataCurrency());
        paymentItems.setDataWords(PaymentUtils.SHA1(amount + merchantCode + sharedKey + invoiceNumber + currency + PaymentUtils.getDeviceId(getMvpView().getViewContext())));
        Log.e("dataWords",paymentItems.getDataWords());
        paymentItems.setDataMerchantChain("NA");
        Log.e("dataMerchantChain",paymentItems.getDataMerchantChain());
        paymentItems.setDataSessionID(String.valueOf(PaymentUtils.digitRandomNo(9)));
        Log.e("dataSessionId",paymentItems.getDataSessionID());
        paymentItems.setDataTransactionID(invoiceNumber);
        Log.e("dataTransactionId",paymentItems.getDataTransactionID());
        paymentItems.setDataMerchantCode(merchantCode);
        Log.e("dataMerchantCode",paymentItems.getDataMerchantCode());
        paymentItems.setDataImei(PaymentUtils.getDeviceId(getMvpView().getViewContext()));
        Log.e("dataImei",paymentItems.getDataImei());
        paymentItems.setMobilePhone("");
        Log.e("mobilePhone",paymentItems.getMobilePhone());
        paymentItems.setPublicKey(getMvpView().getViewContext().getResources().getString(R.string.doku_public_key));
        Log.e("publicKey",paymentItems.getPublicKey());
        paymentItems.isProduction(!BuildConfig.DEBUG);
        return paymentItems;
    }

    @Override
    public void completeDOKUPayment(JSONObject responseJson, String response) {
        if(responseJson!=null) {
            /*DOKUPaymentRequest dokuRequest = new DOKUPaymentRequest();
            dokuRequest.setUsername(getDataManager().getCurrentUserName());
            dokuRequest.setAuthToken(getDataManager().getAccessToken());
            dokuRequest.setLocale(getDataManager().getCurrentUserLocale());
            dokuRequest.setOrderid(invoiceNumber);
            dokuRequest.setTitle("Please Pay");
            dokuRequest.setCurrency(currency);
            dokuRequest.setDokutoken(responseJson.toString());

            dokuRequest.setPrice(responseJson.optString("res_amount", ""));
            dokuRequest.setPayername(responseJson.optString("res_name", ""));
            dokuRequest.setPayerphone(responseJson.optString("res_data_mobile_phone", ""));
            dokuRequest.setPayeremail(responseJson.optString("res_data_email", ""));
            dokuRequest.setDokuchannel(responseJson.optString("res_payment_channel", ""));
            sendDOKUResponseData();*/

            getMvpView().showLoading();
            DOKUMobileRequest dokuMobileRequest = new DOKUMobileRequest(response,getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), invoiceNumber,"15");
            getCompositeDisposable().add(getDataManager()
                    .dokuMobile(dokuMobileRequest)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<BaseResponse>() {
                        @Override
                        public void accept(BaseResponse baseResponse) throws Exception {
                            getMvpView().showMessage(baseResponse.getStatus());
                            if(baseResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                                getMvpView().refreshLayout();
                            }
                            getMvpView().hideLoading();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideLoading();

                            // handle the error here
                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        }
                    }));
        } else {

        }
    }

    @Override
    public void sendDOKUResponseData() {
        getMvpView().showLoading();
        CustPayRequest custPayRequest = new CustPayRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), invoiceNumber);
        getCompositeDisposable().add(getDataManager()
                .cpaybyorderid(custPayRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CustPayResponse>() {
                    @Override
                    public void accept(CustPayResponse custPayResponse) throws Exception {
                        if(custPayResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            Log.e("response", custPayResponse.getStatus());
                            getMvpView().refreshLayout();
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
