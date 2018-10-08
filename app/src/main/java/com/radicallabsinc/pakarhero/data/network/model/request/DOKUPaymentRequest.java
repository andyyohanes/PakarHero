package com.radicallabsinc.pakarhero.data.network.model.request;

import java.io.Serializable;

public class DOKUPaymentRequest implements Serializable {

    private String username;
    private String authToken;
    private String orderid;
    private String title;
    private String price;
    private String currency;
    private String payername;
    private String payeraddress;
    private String payerphone;
    private String payeremail;
    private String dokuchannel;
    private String dokutoken;
    private String locale;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPayername(String payername) {
        this.payername = payername;
    }

    public void setPayeraddress(String payeraddress) {
        this.payeraddress = payeraddress;
    }

    public void setPayerphone(String payerphone) {
        this.payerphone = payerphone;
    }

    public void setPayeremail(String payeremail) {
        this.payeremail = payeremail;
    }

    public void setDokuchannel(String dokuchannel) {
        this.dokuchannel = dokuchannel;
    }

    public void setDokutoken(String dokutoken) {
        this.dokutoken = dokutoken;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
