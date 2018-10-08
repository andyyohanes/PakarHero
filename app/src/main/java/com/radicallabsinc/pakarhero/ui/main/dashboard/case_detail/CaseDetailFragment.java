package com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseDetailDataResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.chat.ChatActivity;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardActivity;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.radicallabsinc.pakarhero.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaseDetailFragment extends BaseFragment implements CaseDetailMvpView {

    @Inject
    CaseDetailMvpPresenter<CaseDetailMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @Inject
    CaseDetailAdapter mAdapter;

    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvCaseTitle)
    TextView tvCaseTitle;

    @BindView(R.id.tvCaseDate)
    TextView tvCaseDate;

    @BindView(R.id.tvExpertise)
    TextView tvExpertise;

    @BindView(R.id.tvPrice)
    TextView tvPrice;

    @BindView(R.id.tvSession)
    TextView tvSession;

    @BindView(R.id.tvTotalPaid)
    TextView tvTotalPaid;

    @BindView(R.id.tvUsedSessions)
    TextView tvUsedSessions;

    @BindView(R.id.tvRemainingSessions)
    TextView tvRemainingSessions;

    @BindView(R.id.tvQuestionContent)
    TextView tvQuestionContent;

    @BindView(R.id.rvDetails)
    RecyclerView rvDetails;

    @BindView(R.id.tvPay)
    TextView tvPay;

    @BindView(R.id.tvChat)
    TextView tvChat;

    @BindView(R.id.tvCloseCase)
    TextView tvCloseCase;

    private TextView tvConfirmDialog, tvPayDialog;
    private Spinner spLength;

    private CaseResponse.CaseData caseData;
    private String caseOwner;
    private List<CaseDetailDataResponse> mList = new ArrayList<>();
    private Integer serviceFee;
    private String totalPaid, orderId;
    private Integer dokuPaymentMethod = 0;
    public static final int PAYPAL_REQUEST_CODE = 7757;

    private static PayPalConfiguration config;

    public static CaseDetailFragment newInstance(CaseResponse.CaseData data,String caseOwnerAs){
        Bundle args = new Bundle();
        args.putSerializable("data",data);
        args.putString("caseOwnerAs",caseOwnerAs);
        CaseDetailFragment fragment = new CaseDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_case_detail, container, false);

        config = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(getResources().getString(R.string.paypal_client_id));

        ActivityComponent component = getActivityComponent();
        if(component!=null){
            caseData = (CaseResponse.CaseData) getArguments().getSerializable("data");
            caseOwner = getArguments().getString("caseOwnerAs");
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            //mAdapter.setCallback(this,expertiseCode);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        final String imgPhoto, name, caseStatus;
        if(caseOwner.equalsIgnoreCase("expert")) {
            imgPhoto = caseData.getCustomerImg();
            name = caseData.getCustomerFirstName();
            tvPay.setVisibility(View.GONE);
        } else {
            imgPhoto = caseData.getExpertImg();
            name = caseData.getExpertFirstName();
        }
        Picasso.with(getContext()).load(imgPhoto).transform(new CircleTransform()).into(ivPhoto);
        tvName.setText(name);
        tvCaseTitle.setText("Case# "+ caseData.getCaseId());
        tvCaseDate.setText(caseData.getCaseStartDate());
        tvExpertise.setText(AppConstants.expertiseMap.get(caseData.getExpertiseCode()));

        tvPrice.setText(caseData.getPricePerSession() + " " + caseData.getCurrency());
        tvSession.setText(caseData.getSessionLength().intValue() + " " + AppConstants.lovMap.get(caseData.getSessionUnit()));
        tvTotalPaid.setText(caseData.getTotalPaid() + " " + caseData.getCurrency());
        tvUsedSessions.setText(caseData.getTotalUsedSessions() + "\n" + AppConstants.lovMap.get(caseData.getSessionUnit()));
        tvRemainingSessions.setText(caseData.getTotalAvailableSessions() + "\n" + AppConstants.lovMap.get(caseData.getSessionUnit()));

        tvQuestionContent.setText(caseData.getDetails().get(caseData.getDetails().size()-1).getQuestion());
        if(caseData.getCaseStatus().equalsIgnoreCase("open")) {
            caseStatus = "open";
        } else {
            caseStatus = "close";
        }

        if(caseStatus.equalsIgnoreCase("close")){
            tvCloseCase.setTextColor(getResources().getColor(R.color.red_button_disable));
            tvPay.setTextColor(getResources().getColor(R.color.red_button_disable));
            tvCloseCase.setClickable(false);
            tvCloseCase.setEnabled(false);
            tvPay.setClickable(false);
            tvPay.setEnabled(false);
        }

        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(caseData.getCaseStatus().equalsIgnoreCase("open"))
                    showDialog();
            }
        });

        //mList.addAll(caseData.getDetails());
        for(int i=0;i<caseData.getDetails().size()-1;i++){
            mList.add(caseData.getDetails().get(i));
        }

        rvDetails.setLayoutManager(mLinearLayoutManager);
        rvDetails.setItemAnimator(new DefaultItemAnimator());
        rvDetails.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mAdapter.addItems(mList);
        rvDetails.setAdapter(mAdapter);

        tvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("caseId",caseData.getCaseId());
                bundle.putString("urlPhoto",imgPhoto);
                bundle.putString("name",name);
                bundle.putString("caseStatus",caseStatus);
                bundle.putLong("roomId",Long.parseLong(caseData.getChatRoomId()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_pay, null);
        dialog.setView(dialogView);
        dialog.setTitle("Payment");

        Integer[] sesLength = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        final TextView tvPricePerSession = (TextView) dialogView.findViewById(R.id.tvPricePerSession);
        spLength = (Spinner) dialogView.findViewById(R.id.session_length_sp);
        final TextView tvSubtotal = (TextView) dialogView.findViewById(R.id.tvSubtotal);
        final TextView tvServiceFee = (TextView) dialogView.findViewById(R.id.tvServiceFee);
        final TextView tvTotal = (TextView) dialogView.findViewById(R.id.tvTotal);
        tvConfirmDialog = (TextView) dialogView.findViewById(R.id.tvConfirm);
        tvPayDialog = (TextView) dialogView.findViewById(R.id.tvPay);

        tvPricePerSession.setText(caseData.getPricePerSession() + " " + caseData.getCurrency() + " / " + caseData.getSessionLength().intValue() + " " + AppConstants.lovMap.get(caseData.getSessionUnit()));
        ArrayAdapter<Integer> adapter_session_length = new ArrayAdapter<Integer>(dialogView.getContext(),android.R.layout.simple_spinner_item,sesLength);
        adapter_session_length.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLength.setAdapter(adapter_session_length);

        Log.e("USD "+String.valueOf(AppConstants.serviceFeeMap.get("USD")),"IDR "+String.valueOf(AppConstants.serviceFeeMap.get("IDR")));

        serviceFee = AppConstants.serviceFeeMap.get(caseData.getCurrency()).intValue();
        tvServiceFee.setText(String.valueOf(serviceFee));

        spLength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvSubtotal.setText(String.valueOf(caseData.getPricePerSession()*Integer.parseInt(spLength.getSelectedItem().toString())));
                totalPaid = String.valueOf(Integer.parseInt(tvSubtotal.getText().toString())+serviceFee);
                tvTotal.setText(caseData.getCurrency()+ " " + totalPaid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvConfirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String payMethod = null;
                if(caseData.getCurrency().equalsIgnoreCase("USD"))
                    payMethod = "PAYPAL";
                else if(caseData.getCurrency().equalsIgnoreCase("IDR"))
                    payMethod = "DOKUCCMOB";
                mPresenter.saveOrder(caseData.getCaseId(),"PH"+caseData.getCaseId()+"-"+CommonUtils.randomString(10)+"-M",serviceFee.doubleValue(),Double.parseDouble(totalPaid)-serviceFee.doubleValue(),caseData.getCurrency(),payMethod);
            }
        });

        tvPayDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("masuk click","ya");
                processPayment();
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    @Override
    public void onSuccessSaveOrder(String orderId) {
        this.orderId = orderId;
        Toast.makeText(getContext(), "Success save order", Toast.LENGTH_SHORT).show();
        tvConfirmDialog.setTextColor(getResources().getColor(R.color.red_button_disable));
        tvPayDialog.setTextColor(getResources().getColor(R.color.red_button));
        tvPayDialog.setEnabled(true);
        tvPayDialog.setClickable(true);
        spLength.setEnabled(false);
        spLength.setClickable(false);
        tvConfirmDialog.setEnabled(false);
        tvConfirmDialog.setClickable(false);
    }

    @Override
    public void processPayment() {
        startPayPalService(config);
        if(caseData.getCurrency().equalsIgnoreCase("USD")){
            startPayPalPaymentActivity(getPaymentforPayPal(),config,PAYPAL_REQUEST_CODE);
        } if(caseData.getCurrency().equalsIgnoreCase("IDR")){
//            startDokuPayment
            mPresenter.performDOKUPayment(1, orderId,totalPaid,caseData.getCurrency());
        }
    }

    @Override
    public void startPayPalPaymentActivity(PayPalPayment thingsToBuy, PayPalConfiguration config, int requestCode) {
        Intent intent = new Intent(getActivity(), com.paypal.android.sdk.payments.PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, thingsToBuy);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public PayPalPayment getPaymentforPayPal() {
        String currency = caseData.getCurrency();
        String price = totalPaid;
        String title = "Please pay";
//        String title = "PakarHero (order ID: " + orderId + ")";
        PayPalPayment payment = new PayPalPayment(new BigDecimal(price),currency,title,PayPalPayment.PAYMENT_INTENT_SALE);
        payment.invoiceNumber(orderId);
        payment.custom(title);
        return payment;
    }

    @Override
    public void startPayPalService(PayPalConfiguration configuration) {
        Intent intent = new Intent(getActivity(),PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        getActivity().startService(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                PaymentConfirmation confirm = data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                JSONObject result = confirm.toJSONObject();
                try {
                    result.put("payment", confirm.getPayment().toJSONObject());
                    Log.e("PAYPAL_TEST", "RESULT DATA: " + result.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("PAYPAL_TEST", "RESULT DATA: " + data.toString());
                }
                mPresenter.sendPayPalResponseData(orderId,result.toString());
            }
        }
    }

    @Override
    public void refreshLayout() {
        ((DashboardActivity)getActivity()).refreshLayout();
    }
}
