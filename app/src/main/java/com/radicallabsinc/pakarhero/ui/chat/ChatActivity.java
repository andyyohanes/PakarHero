package com.radicallabsinc.pakarhero.ui.chat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ChatHistResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseActivity;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends BaseActivity implements ChatMvpView {

    @Inject
    ChatMvpPresenter<ChatMvpView> mPresenter;

    @Inject
    ChatAdapter mAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @BindView(R.id.ivBack)
    ImageView ivBack;

    @BindView(R.id.tvCaseTitle)
    TextView tvCaseTitle;

    @BindView(R.id.tvPersonName)
    TextView tvPersonName;

    @BindView(R.id.ivPersonImage)
    ImageView ivPersonImage;

    @BindView(R.id.rvContent)
    RecyclerView rvContent;

    @BindView(R.id.rlChat)
    RelativeLayout rlChat;

    @BindView(R.id.ivAddPicture)
    ImageView ivAddPicture;

    @BindView(R.id.etChatMessage)
    EditText etChatMessage;

    @BindView(R.id.ivSend)
    ImageView ivSend;

    Bundle bundle;
    long dateStart, dateEnd, roomId;
    long lastMessageId = 0;
    Handler handler;
    Runnable getMessage;
    String imageString, typeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bundle = getIntent().getExtras();
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        roomId = bundle.getLong("roomId");
        tvCaseTitle.setText("Case# " + bundle.get("caseId"));
        tvPersonName.setText(bundle.getString("name"));
        Picasso.with(getBaseContext()).load(bundle.getString("urlPhoto")).transform(new CircleTransform()).into(ivPersonImage);

        etChatMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dateStart = Calendar.getInstance().getTimeInMillis();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateEnd = Calendar.getInstance().getTimeInMillis();
                sendMessage(etChatMessage.getText().toString(), dateStart, dateEnd);
            }
        });

        if(bundle.getString("caseStatus").equalsIgnoreCase("open"))
            rlChat.setVisibility(View.VISIBLE);
        else
            rlChat.setVisibility(View.GONE);

        rvContent.setLayoutManager(mLinearLayoutManager);
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setAdapter(mAdapter);

        mPresenter.onViewPrepared(roomId);
        getMessage();
    }

    @Override
    public void updateChat(List<ChatHistResponse.ChatData> chatDataList, long id) {
        mAdapter.addItems(chatDataList,id,bundle.getString("urlPhoto"));
        rvContent.scrollToPosition(mAdapter.getItemCount()-1);
        lastMessageId = chatDataList.get(mAdapter.getItemCount()-1).getMsgId();
    }

    @Override
    public void updateChat(ChatHistResponse.ChatData chatData) {
        mAdapter.addItems(chatData);
        rvContent.scrollToPosition(mAdapter.getItemCount()-1);
        etChatMessage.setText("");
        lastMessageId = chatData.getMsgId();
    }

    @Override
    public void sendMessage(String content, long epochStart, long epochEnd) {
        mPresenter.sendMessage(roomId, content, epochStart, epochEnd);
    }

    @Override
    public void getMessage() {
        handler = new Handler();
        handler.postDelayed(getMessage = new Runnable() {
            @Override
            public void run() {
                Integer messageId = (int) (long) lastMessageId;
                mPresenter.getMessages(roomId,messageId);
                handler.postDelayed(this,3000);
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(getMessage);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    dateEnd = Calendar.getInstance().getTimeInMillis();
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri,projection,null,null,null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(filepath);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString = Base64.encodeToString(imageBytes,Base64.DEFAULT);
                    typeImage = filepath.substring(filepath.indexOf(".")+1);
                    mPresenter.uploadImage(roomId, imageString, typeImage, dateStart, dateEnd);
                }
                break;
            default:
                break;
        }
    }
}
