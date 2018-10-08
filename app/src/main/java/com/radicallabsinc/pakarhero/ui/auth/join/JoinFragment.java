package com.radicallabsinc.pakarhero.ui.auth.join;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.auth.AuthActivity;
import com.radicallabsinc.pakarhero.ui.auth.verify.VerifyFragment;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.setting.SettingActivity;
import com.radicallabsinc.pakarhero.utils.CommonUtils;
import com.radicallabsinc.pakarhero.utils.ViewUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinFragment extends BaseFragment implements JoinMvpView , View.OnClickListener{

    @Inject
    JoinMvpPresenter<JoinMvpView> mPresenter;

    @BindView(R.id.tvTerms)
    TextView tvTerms;

    @BindView(R.id.cbTerms)
    CheckBox cbTerms;

    @BindView(R.id.username_et)
    EditText etUsername;

    @BindView(R.id.password_et)
    EditText etPassword;

    @BindView(R.id.country_code_et)
    EditText etCountryCode;

    @BindView(R.id.phone_number_et)
    EditText etPhone;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    String username = "";

    public static JoinFragment newInstance(String username){
        Bundle args = new Bundle();
        args.putString("username",username);
        JoinFragment fragment = new JoinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            username = getArguments().getString("username");
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            ViewUtils.setClickableSpanForTerms(tvTerms,getString(R.string.terms_of_use_desc),R.color.colorAccent,this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        ((AuthActivity)getActivity()).setToolbarTitle(R.string.or_join);
        etUsername.setText(username);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvTerms:
                String tag = (String) view.getTag();
                if (tag.equals("terms"))
                    onTermsClicked();
                break;
        }
    }

    @OnClick(R.id.fab)
    public void onJoinClicked(){
        if(checkTerms())
            mPresenter.onJoinClicked(etUsername.getText().toString(), etPassword.getText().toString(), etCountryCode.getText().toString(), etPhone.getText().toString(), CommonUtils.getDeviceLocale(getContext()));
        else
            onError("You must agree to the "+ getResources().getText(R.string.terms_of_use));
    }

    @Override
    public boolean checkTerms() {
        if(cbTerms.isChecked())
            return true;
        else
            return false;
    }

    @Override
    public void onTermsClicked() {
        new MaterialDialog.Builder(getContext())
                .title(R.string.terms_of_use)
                .content("try")
                .positiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void showSuccessDialog(final String username, final String countryCode, final String phone) {
        new MaterialDialog.Builder(getContext())
                .title("SUCCESS")
                .content(R.string.join_success)
                .positiveText("ENTER VERIFICATION CODE")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ((AuthActivity)getActivity()).removeCurrentFragment();
                        ((AuthActivity)getActivity()).showFragment(VerifyFragment.newInstance(username, countryCode, phone));
                    }
                })
                .show();
    }
}
