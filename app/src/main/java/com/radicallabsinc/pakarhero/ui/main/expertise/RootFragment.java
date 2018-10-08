package com.radicallabsinc.pakarhero.ui.main.expertise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;

public class RootFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flMainContainer, ExpertiseFragment.newInstance());
        transaction.commit();
        return view;
    }

    @Override
    protected void setUp(View view) {

    }
}
