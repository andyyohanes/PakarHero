package com.radicallabsinc.pakarhero.ui.setting.skill;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Callback mCallback;
    public List<SkillResponse.SkillData> mList;

    public SkillAdapter(List<SkillResponse.SkillData> mList) {
        this.mList = mList;
    }

    public void setCallback(Callback callback){
        mCallback = callback;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill,parent,false);
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

    public void addItems(List<SkillResponse.SkillData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tvSkillDesc)
        TextView tvSkillDesc;

        @BindView(R.id.tvSkillPrice)
        TextView tvSkillPrice;

        @BindView(R.id.ivSkillPic)
        ImageView ivSkillPic;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(int position){
            super.onBind(position);
            final SkillResponse.SkillData skillData = mList.get(position);
            tvSkillDesc.setText(skillData.getExpertiseDesc());
            if(skillData.getSessionLength()<2)
                tvSkillPrice.setText(skillData.getCurrency()+ " " +skillData.getPricePerSession()+ " / " +skillData.getSessionUnitDesc());
            else
                tvSkillPrice.setText(skillData.getCurrency()+ " " +skillData.getPricePerSession()+ " / " +skillData.getSessionLength()+ " " +skillData.getSessionUnitDesc());
            if(skillData.getSkillImg()!=null){
                Picasso.with(itemView.getContext()).load(skillData.getSkillImg()).into(ivSkillPic);
            }
        }
    }

    public interface Callback{}
}
