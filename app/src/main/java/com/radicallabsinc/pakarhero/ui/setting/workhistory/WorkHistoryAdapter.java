package com.radicallabsinc.pakarhero.ui.setting.workhistory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.WorkHistoryResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkHistoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Callback mCallback;
    public List<WorkHistoryResponse.WorkHistoryData> mList;

    public WorkHistoryAdapter(List<WorkHistoryResponse.WorkHistoryData> mList){
        this.mList = mList;
    }

    public void setCallback(Callback callback){
        mCallback = callback;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_history,parent,false);
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

    public void addItems(List<WorkHistoryResponse.WorkHistoryData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tvWorkHistoryCompany)
        TextView tvWorkHistoryCompany;

        @BindView(R.id.tvWorkHistoryPeriod)
        TextView tvWorkHistoryPeriod;

        @BindView(R.id.tvWorkHistoryDesc)
        TextView tvWorkHistoryDesc;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(int position){
            super.onBind(position);
            final WorkHistoryResponse.WorkHistoryData workHistoryData = mList.get(position);
            tvWorkHistoryCompany.setText(workHistoryData.getCompany());
            tvWorkHistoryPeriod.setText(workHistoryData.getPeriod());
            tvWorkHistoryDesc.setText(workHistoryData.getJobDesc());
        }
    }

    public interface Callback{

    }
}
