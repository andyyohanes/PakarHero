package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpertDetailCertificationAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<CertificationResponse.CertificationData> mList;

    public ExpertDetailCertificationAdapter(List<CertificationResponse.CertificationData> mList){
        this.mList = mList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_certification_expert_detail,parent, false);
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

    public void addItems(List<CertificationResponse.CertificationData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tvCertificationPeriod)
        TextView tvCertificationPeriod;

        @BindView(R.id.tvCertificationDesc)
        TextView tvCertificationDesc;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(final int position){
            super.onBind(position);
            final CertificationResponse.CertificationData data = mList.get(position);
            tvCertificationPeriod.setText(data.getPeriod());
            tvCertificationDesc.setText(data.getCertification());
        }
    }
}
