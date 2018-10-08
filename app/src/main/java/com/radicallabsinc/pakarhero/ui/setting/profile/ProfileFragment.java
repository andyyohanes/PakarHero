package com.radicallabsinc.pakarhero.ui.setting.profile;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.LoginResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.setting.SettingActivity;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment implements ProfileMvpView {

    @Inject
    ProfileMvpPresenter<ProfileMvpView> mPresenter;

    @BindView(R.id.profile_img)
    ImageView ivProfile;

    @BindView(R.id.profile_add_photo_btn)
    ImageButton addPhotoProfile;

    @BindView(R.id.tvUsername)
    TextView tvUsername;

    @BindView(R.id.firstname_et)
    EditText etFirstName;

    @BindView(R.id.lastname_et)
    EditText etLastName;

    @BindView(R.id.phone_number_et)
    EditText etPhoneNumber;

    @BindView(R.id.country_code_et)
    EditText etCountryCode;

    @BindView(R.id.languages_et)
    EditText etLanguages;

    @BindView(R.id.desc_et)
    EditText etDesc;

    @BindView(R.id.paypal_email_et)
    EditText etPaypalEmail;

    @BindView(R.id.bank_name_et)
    EditText etBankName;

    @BindView(R.id.bank_account_name_et)
    EditText etBankActName;

    @BindView(R.id.bank_account_number_et)
    EditText etBankActNumber;

    @BindView(R.id.bank_branch_name_et)
    EditText etBankBranch;

    String typeImage, imageString;

    public static ProfileFragment newInstance(){
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if(component != null){
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        ((SettingActivity)getActivity()).enableNavigationIcon();
        mPresenter.onViewPrepared();
    }

    @Override
    public void getProfile(LoginResponse.LoginData loginData) {
        tvUsername.setText(loginData.getUserName());
        etFirstName.setText(loginData.getFirstName());
        etLastName.setText(loginData.getLastName());
        etCountryCode.setText(loginData.getCountryCode());
        etPhoneNumber.setText(loginData.getPhone());
        etLanguages.setText(loginData.getLanguages());
        etDesc.setText(loginData.getUserDesc());
        String photo = loginData.getUserImg();
        typeImage = photo.substring(photo.indexOf(".")+1);
        Picasso.with(getContext()).load(loginData.getUserImg()).transform(new CircleTransform()).into(ivProfile);
        new DownloadImageTask(ivProfile).execute(loginData.getUserImg());
        etPaypalEmail.setText(loginData.getPaypalEmail());
        etBankActName.setText(loginData.getDokuBankActName());
        etBankActNumber.setText(loginData.getDokuBankActNumber());
        etBankBranch.setText(loginData.getDokuBranch());
        etBankName.setText(loginData.getDokuBankName());

        addPhotoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        public DownloadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] imageBytes = baos.toByteArray();
            imageString = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlOfImage = strings[0];
            Bitmap photo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                photo = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return photo;
        }
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
                    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(output);
                    final Paint paint = new Paint();
                    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

                    paint.setAntiAlias(true);
                    canvas.drawARGB(0, 0, 0, 0);
                    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                    canvas.drawBitmap(bitmap, rect, rect, paint);
                    ivProfile.setImageBitmap(output);
                    //String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bitmap,"Title",null);
                    //Picasso.with(getContext()).load(filepath).transform(new CircleTransform()).into(ivProfile);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString = Base64.encodeToString(imageBytes,Base64.DEFAULT);
                    typeImage = filepath.substring(filepath.indexOf(".")+1);


                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.fab)
    @Override
    public void onClickSave() {
        String firstName, lastName, desc, languages, paypalEmail, bankActName, bankActNumber, bankName, bankBranch;
        firstName = etFirstName.getText()==null ? "" : etFirstName.getText().toString();
        lastName = etLastName.getText()==null ? "" : etLastName.getText().toString();
        desc = etDesc.getText()==null ? "" : etDesc.getText().toString();
        languages = etLanguages.getText()==null ? "" : etLanguages.getText().toString();
        paypalEmail = etPaypalEmail.getText()==null ? "" : etPaypalEmail.getText().toString();
        bankActName = etBankActName.getText()==null ? "" : etBankActName.getText().toString();
        bankActNumber = etBankActNumber.getText()==null ? "" : etBankActNumber.getText().toString();
        bankName = etBankName.getText()==null ? "" : etBankName.getText().toString();
        bankBranch = etBankBranch.getText()==null ? "" : etBankBranch.getText().toString();
        Log.e("imageType",typeImage);
        //Log.e("lastname",lastName);
        mPresenter.saveProfile(firstName, lastName, etCountryCode.getText().toString(), etPhoneNumber.getText().toString(), desc, languages,
                paypalEmail, bankActName, bankActNumber, bankName, bankBranch, imageString, typeImage);
    }

    @Override
    public void goToMainActivity() {
        ((SettingActivity)getActivity()).endActivity();
    }
}
