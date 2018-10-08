package com.radicallabsinc.pakarhero.ui.auth.verify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyFragment extends BaseFragment implements VerifyMvpView {

    @Inject
    VerifyMvpPresenter<VerifyMvpView> mPresenter;

    @BindView(R.id.verify_et)
    EditText etVerify;

    String username,phone,countryCode;

    public static VerifyFragment newInstance(String username, String countryCode, String phone){
        Bundle args = new Bundle();
        args.putString("userName", username);
        args.putString("countryCode", countryCode);
        args.putString("phone",phone);
        VerifyFragment fragment = new VerifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verify, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            username = getArguments().getString("userName");
            phone = getArguments().getString("phone");
            countryCode = getArguments().getString("countryCode");
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        
    }

    @OnClick(R.id.send_new_code)
    @Override
    public void sendNewCodeClicked() {
        mPresenter.sendNewCode(username,phone);
    }

    @OnClick(R.id.fab)
    @Override
    public void verifyClicked() {
        mPresenter.verifyCode(username, countryCode, phone, etVerify.getText().toString());
    }
}
