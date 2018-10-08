package com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseDetailDataResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CaseDetailAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<CaseDetailDataResponse> mList;

    public CaseDetailAdapter(List<CaseDetailDataResponse> mList){
        this.mList = mList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_case,parent,false);
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

    public void addItems(List<CaseDetailDataResponse> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tvQuestion)
        TextView tvQuestion;

        @BindView(R.id.tvAnswer)
        TextView tvAnswer;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(int position){
            super.onBind(position);
            CaseDetailDataResponse caseDetailDataResponse = mList.get(position);
            tvQuestion.setText(caseDetailDataResponse.getQuestion());
            tvAnswer.setText(caseDetailDataResponse.getAnswer());
        }
    }
}
