package com.radicallabsinc.pakarhero.ui.chat;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ChatHistResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int ITEM_RECEIVED = 1;
    private static final int ITEM_SENT = 2;
    private List<ChatHistResponse.ChatData> mList;
    private long myId;
    private String imgUrl;

    public ChatAdapter(List<ChatHistResponse.ChatData> mList){
        this.mList = mList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            default: case ITEM_RECEIVED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received,parent,false);
                return new ItemReceivedViewHolder(view);
            case ITEM_SENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent,parent,false);
                return new ItemSentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItems(List<ChatHistResponse.ChatData> mList,long myId, String imgUrl){
        this.mList = mList;
        this.myId = myId;
        this.imgUrl = imgUrl;
        notifyDataSetChanged();
        Log.e("isi",String.valueOf(mList.size()));
        for(int i=0;i<getItemCount();i++){
            Log.e("isi",""+mList.toString());
        }
        Log.e("id",String.valueOf(myId));
    }

    public void addItems(ChatHistResponse.ChatData chatData){
        this.mList.add(chatData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(mList.get(position).getReceiverId() == myId)
            return ITEM_RECEIVED;
        else
            return ITEM_SENT;
    }

    public class ItemReceivedViewHolder extends BaseViewHolder{

        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;

        @BindView(R.id.tvMessage)
        TextView tvMessage;

        @BindView(R.id.ivMessage)
        ImageView ivMessage;

        @BindView(R.id.tvMessageTime)
        TextView tvMessageTime;

        public ItemReceivedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final ChatHistResponse.ChatData chatData = mList.get(position);
            Date date = new Date();
            date.setTime(chatData.getEpochEnd());
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            String timeChat = sdfTime.format(date);
            Picasso.with(itemView.getContext()).load(imgUrl).transform(new CircleTransform()).into(ivPhoto);
            if(chatData.getContentType().equalsIgnoreCase("text")) {
                tvMessage.setText(chatData.getContent());
            } else if(chatData.getContentType().equalsIgnoreCase("image")){
                ivMessage.setVisibility(View.VISIBLE);
                tvMessage.setVisibility(View.GONE);
                Picasso.with(itemView.getContext()).load(chatData.getContent()).resize(240,240).into(ivMessage);
            }
            tvMessageTime.setText(timeChat);
        }
    }

    public class ItemSentViewHolder extends BaseViewHolder{

        @BindView(R.id.tvMessage)
        TextView tvMessage;

        @BindView(R.id.ivMessage)
        ImageView ivMessage;

        @BindView(R.id.tvMessageTime)
        TextView tvMessageTime;

        public ItemSentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final ChatHistResponse.ChatData chatData = mList.get(position);
            Date date = new Date();
            date.setTime(chatData.getEpochEnd());
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            String timeChat = sdfTime.format(date);
            if(chatData.getContentType().equalsIgnoreCase("text")) {
                tvMessage.setText(chatData.getContent());
            } else if(chatData.getContentType().equalsIgnoreCase("image")){
                ivMessage.setVisibility(View.VISIBLE);
                tvMessage.setVisibility(View.GONE);
                Picasso.with(itemView.getContext()).load(chatData.getContent()).resize(240,240).into(ivMessage);
            }
            tvMessageTime.setText(timeChat);
        }
    }
}
