package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ReviewResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<ReviewResponse.ReviewData> mList;

    public ReviewAdapter(List<ReviewResponse.ReviewData> mList){
        this.mList = mList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review,parent, false);
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

    public void addItems(List<ReviewResponse.ReviewData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.ivCustomerImg)
        ImageView ivCustomerImg;
        @BindView(R.id.tvCustomerName)
        TextView tvCustomerName;
        @BindView(R.id.tvReviewDate)
        TextView tvReviewDate;
        @BindView(R.id.rbReview)
        RatingBar rbReview;
        @BindView(R.id.tvComment)
        TextView tvComment;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(final int position){
            super.onBind(position);
            final ReviewResponse.ReviewData data = mList.get(position);
            Picasso.with(itemView.getContext()).load(data.getCustomerImg()).transform(new CircleTransform()).into(ivCustomerImg);
            tvCustomerName.setText(data.getCustomerFirstName());
            tvReviewDate.setText(data.getReviewDate());
            rbReview.setRating(data.getRating());
            tvComment.setText(data.getComment());
        }
    }
}
