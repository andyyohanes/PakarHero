package com.radicallabsinc.pakarhero.ui.setting.certification;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.setting.SettingActivity;
import com.radicallabsinc.pakarhero.ui.setting.SwipeController;
import com.radicallabsinc.pakarhero.ui.setting.SwipeControllerActions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CertificationFragment extends BaseFragment implements CertificationMvpView{

    @Inject
    CertificationMvpPresenter<CertificationMvpView> mPresenter;

    @Inject
    CertificationAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.rvContent)
    RecyclerView rvContent;

    public static CertificationFragment newInstance(){
        Bundle args = new Bundle();
        CertificationFragment fragment = new CertificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_content, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        ((SettingActivity)getActivity()).enableNavigationIcon();

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(mLayoutManager);
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setAdapter(mAdapter);

        final SwipeController swipeController = new SwipeController(getActivity(), new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mPresenter.deleteItem(mAdapter.mList.get(position), position);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(rvContent);

        rvContent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        mPresenter.onViewPrepared();
    }

    @Override
    public void getCertification(List<CertificationResponse.CertificationData> certificationList) {
        mAdapter.addItems(certificationList);
    }

    @Override
    public void onSuccessDeleteItem(int position) {
        mAdapter.mList.remove(position);
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
    }

    @Override
    public void onSuccessSaveItem() {
        mAdapter.mList.clear();
        mPresenter.onViewPrepared();
    }

    @OnClick(R.id.fab)
    @Override
    public void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_certification, null);
        dialog.setView(dialogView);

        final EditText etPeriod = (EditText) dialogView.findViewById(R.id.period_et);
        final EditText etDesc = (EditText) dialogView.findViewById(R.id.desc_et);

        dialog.setTitle("Add Certification");
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.saveItem(etPeriod.getText().toString(), etDesc.getText().toString());
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    @Override
    public void goToMainActivity() {
        ((SettingActivity)getActivity()).endActivity();
    }
}
