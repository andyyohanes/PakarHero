package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review;

import com.radicallabsinc.pakarhero.data.network.model.response.ReviewResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface ReviewMvpView extends MvpView {

    void updateReview(List<ReviewResponse.ReviewData> reviewList);
}
