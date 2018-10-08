package com.radicallabsinc.pakarhero.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.ui.base.BaseActivity;
import com.radicallabsinc.pakarhero.ui.main.MainActivity;
import com.radicallabsinc.pakarhero.ui.setting.certification.CertificationFragment;
import com.radicallabsinc.pakarhero.ui.setting.profile.ProfileFragment;
import com.radicallabsinc.pakarhero.ui.setting.skill.SkillFragment;
import com.radicallabsinc.pakarhero.ui.setting.workhistory.WorkHistoryFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements SettingMvpView {

    @Inject
    SettingMvpPresenter<SettingMvpView> mPresenter;

    @BindView(R.id.mToolbar)
    Toolbar mToolbar;

    @BindView(R.id.spMenu)
    Spinner spMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        spMenu.setAdapter(new MyMenuAdapter(mToolbar.getContext(),new String[]{"Profile","Certification","Skill","Work History"}));
        spMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                removeCurrentFragment();
                switch (position){
                    case 0:
                        showFragment(ProfileFragment.newInstance());
                        break;
                    case 1:
                        showFragment(CertificationFragment.newInstance());
                        break;
                    case 2:
                        showFragment(SkillFragment.newInstance());
                        break;
                    case 3:
                        showFragment(WorkHistoryFragment.newInstance());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
    public void removeCurrentFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFrag = getSupportFragmentManager().findFragmentById(R.id.mainContainer);
        if(currentFrag!=null)
            fragmentTransaction.remove(currentFrag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void enableNavigationIcon() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private static class MyMenuAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyMenuAdapter(Context context, String[] objects){
            super(context, android.R.layout.simple_list_item_1,objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public void setDropDownViewTheme(@Nullable Resources.Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }

        @Nullable
        @Override
        public Resources.Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view;

            if(convertView==null){
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }
    }

    @Override
    public void endActivity() {
        MainActivity.activity.finish();
        finish();
        startActivity(new Intent(SettingActivity.this, MainActivity.class));
    }
}
