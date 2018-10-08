package com.radicallabsinc.pakarhero.ui.main.expertise;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpertiseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<ExpertiseResponse.ExpertiseData> mList;

    public ExpertiseAdapter(List<ExpertiseResponse.ExpertiseData> mList) {
        this.mList = mList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expertise,parent,false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItems(List<ExpertiseResponse.ExpertiseData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.cvExpertise)
        CardView cvExpertise;

        @BindView(R.id.ivExpertise)
        ImageView ivExpertise;

        @BindView(R.id.tvExpertise)
        TextView tvExpertiseDesc;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(int position){
            super.onBind(position);
            final ExpertiseResponse.ExpertiseData data = mList.get(position);
            tvExpertiseDesc.setText(data.getExpertiseDesc());
            Picasso.with(itemView.getContext()).load(data.getMobileIconName()).resize(128,128).into(ivExpertise);
            int color = Color.parseColor("#"+ data.getIconColorCode());
            ivExpertise.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
            cvExpertise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onClickItem(data.getExpertiseCode());
                }
            });
        }
    }

    public interface Callback {
        void onClickItem(String expertiseCode);
    }
}
