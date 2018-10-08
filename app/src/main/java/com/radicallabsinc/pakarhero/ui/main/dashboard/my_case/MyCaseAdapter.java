package com.radicallabsinc.pakarhero.ui.main.dashboard.my_case;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseViewHolder;
import com.radicallabsinc.pakarhero.ui.main.dashboard.ViewType;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int ITEM_TYPE = 1;
    private static final int HEADER_TYPE = 2;
    private List<CaseResponse.CaseData> caseList;
    private List<String> caseTypeList;
    private HashMap<String, Integer> caseTypeMap = new HashMap<String, Integer>();

    private SparseArray<ViewType> viewTypes;
    private SparseIntArray headerExpandTracker;

    private Callback mCallback;

    public MyCaseAdapter(List<CaseResponse.CaseData> caseList){
        this.caseList = caseList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            default: case ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_case,parent,false);
                return new ViewHolder(view);
            case HEADER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_case,parent,false);
                return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(caseTypeList != null && caseList != null){
            viewTypes.clear();
            int collapsedCount = 0;
            for(int i = 0; i < caseTypeList.size();i++){
                viewTypes.put(count, new ViewType(i, HEADER_TYPE));
                count += 1;
                String caseType = caseTypeList.get(i);
                int childCount = caseTypeMap.get(caseType);
                if(headerExpandTracker.get(i) == 0){
                    for (int j = 0; j < childCount; j++) {
                        viewTypes.put(count, new ViewType(count - (i + 1) + collapsedCount, ITEM_TYPE));
                        count += 1;
                    }
                } else {
                    collapsedCount += childCount;
                }
            }
        }
        return count;
    }

    public void addItems(List<CaseResponse.CaseData> caseList, List<String> caseTypeList, List<Integer> caseTypeSize){
        if(caseList != null && caseTypeList != null){
            this.caseList = caseList;
            this.caseTypeList = caseTypeList;
            caseTypeMap.put("Active Case",caseTypeSize.get(0));
            caseTypeMap.put("Inactive Case",caseTypeSize.get(1));
            viewTypes = new SparseArray<>(caseList.size()+caseTypeList.size());
            headerExpandTracker = new SparseIntArray(caseTypeList.size());
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(viewTypes.get(position).getType() == HEADER_TYPE)
            return HEADER_TYPE;
        else
            return ITEM_TYPE;
    }

    public void onHeaderClick(int position) {
//        ViewType viewType = viewTypes.get(position);
        int dataIndex = position;
//        Log.e("dataIndex", String.valueOf(position)+ " " +String.valueOf(dataIndex)+ " " + String.valueOf(headerExpandTracker.get(dataIndex)));*/
        String caseType = caseTypeList.get(dataIndex);
        int childCount = caseTypeMap.get(caseType);
        final int positionStart = position+1;
        if (headerExpandTracker.get(dataIndex) != 0) {
            // Collapsed. Now expand it
            headerExpandTracker.put(dataIndex, 0);
            notifyItemRangeInserted(positionStart, childCount);
        } else {
            // Expanded. Now collapse it
            headerExpandTracker.put(dataIndex, 1);
            notifyItemRangeRemoved(positionStart, childCount);
        }
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.cvCaseItem)
        CardView cvCaseItem;

        @BindView(R.id.tvCaseTitle)
        TextView tvCaseTitle;

        @BindView(R.id.tvCaseDate)
        TextView tvCaseDate;

        @BindView(R.id.tvExpertise)
        TextView tvExpertise;

        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(int position){
            super.onBind(position);
            int dataIndex = viewTypes.get(position).getDataIndex();
            final CaseResponse.CaseData data = caseList.get(dataIndex);
            tvCaseTitle.setText("Case# "+ data.getCaseId());
            if(data.getCaseStatus().equalsIgnoreCase("close"))
                tvCaseDate.setText(data.getCaseCloseDate());
            else
                tvCaseDate.setText(data.getCaseStartDate());
            tvExpertise.setText(AppConstants.expertiseMap.get(data.getExpertiseCode()));
            Picasso.with(itemView.getContext()).load(data.getExpertImg()).transform(new CircleTransform()).into(ivPhoto);

            cvCaseItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onClickItem(data);
                }
            });
        }
    }

    public class HeaderViewHolder extends BaseViewHolder {

        @BindView(R.id.tvHeaderCase)
        TextView tvHeaderCase;

        @BindView(R.id.llHeaderCase)
        LinearLayout llHeaderCase;

        public HeaderViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void clear(){

        }

        public void onBind(final int position){
            super.onBind(position);
            final int dataIndex = viewTypes.get(position).getDataIndex();
            final String data = caseTypeList.get(dataIndex);
            tvHeaderCase.setText(data);
            llHeaderCase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onHeaderClick(dataIndex);
                }
            });
        }
    }

    public interface Callback {
        void onClickItem(CaseResponse.CaseData data);
    }
}
