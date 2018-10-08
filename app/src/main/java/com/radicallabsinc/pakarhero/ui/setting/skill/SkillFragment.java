package com.radicallabsinc.pakarhero.ui.setting.skill;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.LovResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.setting.SettingActivity;
import com.radicallabsinc.pakarhero.ui.setting.SwipeController;
import com.radicallabsinc.pakarhero.ui.setting.SwipeControllerActions;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class SkillFragment extends BaseFragment implements SkillMvpView {

    private ArrayList<String> listFieldOfExpertise = new ArrayList<>();
    private ArrayList<String> listExpertiseCode = new ArrayList<>();
    private ArrayList<String> listSessionDesc = new ArrayList<>();
    private ArrayList<String> listSessionUnit = new ArrayList<>();
    private String expertiseCode, sessionUnit;
    private String imageString="", typeImage="";

    @Inject
    SkillMvpPresenter<SkillMvpView> mPresenter;

    @Inject
    SkillAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.rvContent)
    RecyclerView rvContent;

    ImageView ivPicture;
    public static SkillFragment newInstance(){
        Bundle args = new Bundle();
        SkillFragment fragment = new SkillFragment();
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
    public void getSkill(List<SkillResponse.SkillData> skillList) {
        mAdapter.addItems(skillList);
    }

    @Override
    public void getExpertiseData(List<ExpertiseResponse.ExpertiseData> expertiseList) {
        for(int i=0;i<expertiseList.size();i++){
            listFieldOfExpertise.add(expertiseList.get(i).getExpertiseDesc());
            listExpertiseCode.add(expertiseList.get(i).getExpertiseCode());
        }
    }

    @Override
    public void getLovData(List<LovResponse.LovData> lovList) {
        for(int i=0;i<lovList.size();i++){
            listSessionDesc.add(lovList.get(i).getLovDesc());
            listSessionUnit.add(lovList.get(i).getLovValue());
        }
    }

    @Override
    public void onSuccessDeleteItem(int position) {
        mAdapter.mList.remove(position);
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
    }

    @OnClick(R.id.fab)
    @Override
    public void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_skill, null);
        dialog.setView(dialogView);

        Integer[] sesLength = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        String[] currency = new String[]{"USD","IDR"};

        final EditText etPrice = (EditText) dialogView.findViewById(R.id.price_et);
        final Spinner spFieldOfExpertise = (Spinner) dialogView.findViewById(R.id.field_of_expertise_sp);
        final Spinner spCurrency = (Spinner) dialogView.findViewById(R.id.currency_sp);
        final Spinner spSessionLength = (Spinner) dialogView.findViewById(R.id.session_length_sp);
        final Spinner spSessionUnit = (Spinner) dialogView.findViewById(R.id.session_unit_sp);
        ivPicture = (ImageView) dialogView.findViewById(R.id.picture_iv);

        ArrayAdapter<String> adapter_field_expertise = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_spinner_item, listFieldOfExpertise);
        adapter_field_expertise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFieldOfExpertise.setAdapter(adapter_field_expertise);
        ArrayAdapter<String> adapter_session_unit = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_spinner_item, listSessionDesc);
        adapter_session_unit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSessionUnit.setAdapter(adapter_session_unit);
        ArrayAdapter<Integer> adapter_session_length = new ArrayAdapter<Integer>(dialogView.getContext(),android.R.layout.simple_spinner_item,sesLength);
        adapter_session_length.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSessionLength.setAdapter(adapter_session_length);
        ArrayAdapter<String> adapter_currency = new ArrayAdapter<String>(dialogView.getContext(),android.R.layout.simple_spinner_item,currency);
        adapter_currency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurrency.setAdapter(adapter_currency);
        spFieldOfExpertise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                expertiseCode = listExpertiseCode.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spSessionUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sessionUnit = listSessionUnit.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ivPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

        dialog.setTitle("Add Certification");
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.saveItem(expertiseCode,spCurrency.getSelectedItem().toString(),Integer.parseInt(etPrice.getText().toString()),Integer.parseInt(spSessionLength.getSelectedItem().toString()),sessionUnit,imageString,typeImage);
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    @Override
    public void onSuccessSaveItem() {
        mAdapter.mList.clear();
        mPresenter.onViewPrepared();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getContentResolver().query(uri,projection,null,null,null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                    //Drawable drawable = new BitmapDrawable(bitmap);
                    //Log.e("masuk sini",bitmap.toString()+ " " + filepath);
                    ivPicture.setImageBitmap(bitmap);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString = Base64.encodeToString(imageBytes,Base64.DEFAULT);
                    typeImage = filepath.substring(filepath.indexOf(".")+1);
                    /*Log.e("imageString upload", imageString);
                    Log.e("typeImage upload",filepath.substring(filepath.indexOf(".")+1));*/
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void goToMainActivity() {
        ((SettingActivity)getActivity()).endActivity();
    }
}
