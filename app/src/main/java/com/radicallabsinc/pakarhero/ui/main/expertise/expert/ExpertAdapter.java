package com.radicallabsinc.pakarhero.ui.main.expertise.expert;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpertAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<ExpertResponse.ExpertData> mList;
    private String expertiseCode;
    private ArrayList<Integer> mPosition;

    public ExpertAdapter(List<ExpertResponse.ExpertData> mList){
        this.mList = mList;
    }

    public void setCallback(Callback callback, String expertiseCode){
        mCallback = callback;
        this.expertiseCode = expertiseCode;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expert,parent, false);
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

    public void addItems(List<ExpertResponse.ExpertData> mList, ArrayList<Integer> mPosition){
        this.mList = mList;
        this.mPosition = mPosition;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.ivExpertSkill)
        ImageView ivExpertSkill;

        @BindView(R.id.tvPricePerSession)
        TextView tvPricePerSession;

        @BindView(R.id.rbExpert)
        RatingBar rbExpert;

        @BindView(R.id.tvCountRatings)
        TextView tvCountRatings;

        @BindView(R.id.tvDesc)
        TextView tvDesc;

        @BindView(R.id.ivExpertImage)
        ImageView ivExpertImage;

        @BindView(R.id.tvExpertName)
        TextView tvExpertName;

        @BindView(R.id.tvDetail)
        TextView tvDetail;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(final int position){
            super.onBind(position);
            final ExpertResponse.ExpertData data = mList.get(position);
            final Integer skillPosition = mPosition.get(position);
            final SkillResponse.SkillData skillData = data.getExpertSkills().get(skillPosition);

            if(skillData.getSkillImg()!=null)
                Picasso.with(itemView.getContext()).load(skillData.getSkillImg()).into(ivExpertSkill);
            else
                Picasso.with(itemView.getContext()).load(data.getExpertImg()).into(ivExpertSkill);
            if(skillData.getSessionLength()>1)
                tvPricePerSession.setText(skillData.getCurrency()+ " " + skillData.getPricePerSession() + " / "
                                        + skillData.getSessionLength() + " " + skillData.getSessionUnit());
            else
                tvPricePerSession.setText(skillData.getCurrency()+ " " + skillData.getPricePerSession() + " / "
                        + skillData.getSessionUnit());
            if(data.getExpertCountRatings()>0)
                rbExpert.setRating(data.getExpertSumRatings()/data.getExpertCountRatings());
            tvCountRatings.setText("("+data.getExpertCountRatings()+" reviews)");
            if(data.getExpertDesc()!=null)
                tvDesc.setText(data.getExpertDesc());
            else
                tvDesc.setText("No Description");
            tvExpertName.setText(data.getExpertFirstName());
            Picasso.with(itemView.getContext()).load(data.getExpertImg()).transform(new CircleTransform()).into(ivExpertImage);
            tvDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onItemClicked(position,skillPosition);
                }
            });
        }
    }

    public interface Callback {
        void onItemClicked(Integer position, Integer skillPosition);
    }
}
