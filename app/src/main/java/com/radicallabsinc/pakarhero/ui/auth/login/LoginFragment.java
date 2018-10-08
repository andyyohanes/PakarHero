package com.radicallabsinc.pakarhero.ui.auth.login;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.auth.AuthActivity;
import com.radicallabsinc.pakarhero.ui.auth.join.JoinFragment;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.main.MainActivity;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.CommonUtils;
import com.radicallabsinc.pakarhero.utils.ViewUtils;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginFragment extends BaseFragment implements LoginMvpView, View.OnClickListener {

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.login_enter_username_l)
    LinearLayout llJoinHint;

    @BindView(R.id.login_password_layout)
    RelativeLayout passwordLayout;

    @BindView(R.id.login_enter_email)
    TextView tvEnterEmail;

    @BindView(R.id.username_et)
    EditText etUsername;

    @BindView(R.id.password_et)
    EditText etPassword;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    String token;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            ViewUtils.setClickableSpanForSignUp(tvEnterEmail,getString(R.string.enter_email),R.color.light_blue,this);
            getPushToken();
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        ((AuthActivity)getActivity()).enableNavigationIcon();
        ((AuthActivity)getActivity()).setToolbarTitle(R.string.log_in);
    }

    @Override
    public boolean isPasswordVisible() {
        return passwordLayout.getVisibility() == View.VISIBLE;
    }

    @OnClick(R.id.fab)
    public void onActionClicked(){
        mPresenter.onActionClicked();
    }

    @Override
    public void changePasswordLayoutVisibility(boolean isVisible) {
        passwordLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        if(!isVisible) etPassword.setText("");
    }

    @Override
    public void setFocusToPassword() {
        etPassword.requestFocus();
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return CommonUtils.getMD5Hex(etPassword.getText().toString());
    }

    @Override
    public void goToMainActivity() {
        ((AuthActivity)getActivity()).endActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_enter_email:
                String tag = (String) view.getTag();
                if (tag.equals("join"))
                    onJoinClicked("");
                break;
        }
    }

    @Override
    public void onJoinClicked(String username) {
        ((AuthActivity)getActivity()).showFragment(JoinFragment.newInstance(username));
    }

    @Override
    public void changeTitle() {
        ((AuthActivity)getActivity()).setToolbarTitle(R.string.sign_in);
    }

    @OnTextChanged(R.id.username_et)
    public void onUsernameChanged(CharSequence text){
        mPresenter.onUsernameChanged(text.toString());
    }

    @Override
    public void hideJoin() {
        llJoinHint.setVisibility(View.GONE);
        ((AuthActivity)getActivity()).setToolbarTitle(R.string.sign_in);
    }

    @Override
    public void showJoin() {
        llJoinHint.setVisibility(View.VISIBLE);
        ((AuthActivity)getActivity()).setToolbarTitle(R.string.log_in);
    }

    @Override
    public void onErrorShow(String message) {
        if(message.equalsIgnoreCase(AppConstants.ERROR_WRONG_NAME_OR_PASSWORD))
            new MaterialDialog.Builder(getContext())
                .title("ERROR")
                .content(R.string.wrong_name_password)
                .positiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick(R.id.forgot_msg)
    @Override
    public void showDialogForgotPwd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_forgot_pwd, null);
        dialog.setView(dialogView);

        final EditText etUsername = (EditText) dialogView.findViewById(R.id.username_et);

        dialog.setTitle("Forgot Password");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.forgotPwd(etUsername.getText().toString(), CommonUtils.getDeviceLocale(getContext()));
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    @Override
    public void showSuccessDialog() {
        new MaterialDialog.Builder(getContext())
                .title("SUCCESS")
                .content(R.string.forgot_password_success)
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
    public String getPushToken() {
        Log.e("masuk get token","ya");
        Log.e("gettoken", "" + FirebaseInstanceId.getInstance().getToken());
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
                Log.e("newToken", "" + token);
//                getActivity().getPreferences(Context.MODE_PRIVATE).edit().putString("fb", newToken).apply();
            }
        });
        return token;
    }
}
