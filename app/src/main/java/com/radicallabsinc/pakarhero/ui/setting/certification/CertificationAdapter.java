package com.radicallabsinc.pakarhero.ui.setting.certification;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CertificationAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    public List<CertificationResponse.CertificationData> mList;

    public CertificationAdapter(List<CertificationResponse.CertificationData> mList){
        this.mList = mList;
    }

    public void setCallback(Callback callback){
        mCallback = callback;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_certification,parent,false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if(mList==null)
            return 0;
        else
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

        public void onBind(int position){
            super.onBind(position);
            final CertificationResponse.CertificationData certificationData = mList.get(position);
            tvCertificationPeriod.setText(certificationData.getPeriod());
            tvCertificationDesc.setText(certificationData.getCertification());
        }
    }

    public interface Callback{

    }

}
