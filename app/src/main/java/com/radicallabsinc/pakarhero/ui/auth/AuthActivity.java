package com.radicallabsinc.pakarhero.ui.auth;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.ui.auth.login.LoginFragment;
import com.radicallabsinc.pakarhero.ui.auth.verify.VerifyFragment;
import com.radicallabsinc.pakarhero.ui.base.BaseActivity;
import com.radicallabsinc.pakarhero.ui.main.MainActivity;
import com.radicallabsinc.pakarhero.ui.setting.profile.ProfileFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthActivity extends BaseActivity implements AuthMvpView {

    @Inject
    AuthMvpPresenter<AuthMvpView> mPresenter;

    @BindView(R.id.mToolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        FirebaseApp.initializeApp(this);
        setUp();
    }

    @Override
    protected void setUp() {
        showFragment(LoginFragment.newInstance());
    }

    @Override
    public void showFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, fragment, TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void backStackFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
            removeCurrentFragment();
        }
    }

    @Override
    public void removeCurrentFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFrag = getSupportFragmentManager()
                .findFragmentById(R.id.mainContainer);

        if (currentFrag != null) {
            transaction.remove(currentFrag);
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void setToolbarTitle(int resId) {
        mToolbar.setTitle(resId);
    }

    @Override
    public void enableNavigationIcon() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backStackFragment();
            }
        });
    }

    @Override
    public void disableNavigationIcon() {
        mToolbar.setNavigationIcon(null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(getSupportFragmentManager().getBackStackEntryCount()==0){
            finish();
        }
    }

    @Override
    public void endActivity() {
        finish();
        MainActivity.activity.finish();
        startActivity(new Intent(AuthActivity.this, MainActivity.class));
    }
}
