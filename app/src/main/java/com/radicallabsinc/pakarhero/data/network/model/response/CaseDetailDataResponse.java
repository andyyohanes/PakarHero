package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CaseDetailDataResponse implements Serializable {
    @SerializedName("questionType")
    @Expose
    private String questionType;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("caseDetailId")
    @Expose
    private Integer caseDetailId;

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getCaseDetailId() {
        return caseDetailId;
    }
}
