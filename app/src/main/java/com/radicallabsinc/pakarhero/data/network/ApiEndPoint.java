package com.radicallabsinc.pakarhero.data.network;

import com.radicallabsinc.pakarhero.BuildConfig;

public final class ApiEndPoint {

    public static final String CONTENT_TYPE = "application/json; charset-utf-8";

    public static final String CHECK_SESSION = BuildConfig.BASE_URL + "checksession";

    public static final String GET_EXPERTISE = BuildConfig.BASE_URL + "getexpertise";
    public static final String GET_LOV = BuildConfig.BASE_URL + "getlov";
    public static final String GET_EXPERTS = BuildConfig.BASE_URL + "getexperts";
    public static final String GET_REVIEWS = BuildConfig.BASE_URL + "getreviews";

    public static final String CHECK_USERNAME = BuildConfig.BASE_URL + "checkname";
    public static final String LOGIN = BuildConfig.BASE_URL + "login";
    public static final String LOGOUT = BuildConfig.BASE_URL + "logout";
    public static final String JOIN = BuildConfig.BASE_URL + "join";
    public static final String FORGOT_PASSWORD = BuildConfig.BASE_URL + "forgotpwd";
    public static final String RESET_PASSWORD = BuildConfig.BASE_URL + "resetpwd";
    public static final String VERIFY = BuildConfig.BASE_URL + "verify";
    public static final String NEW_CODE = BuildConfig.BASE_URL + "newcode";

    public static final String APPLY_EXPERT = BuildConfig.BASE_URL + "applyexpert";

    public static final String GET_PROFILE = BuildConfig.BASE_URL + "getprofile";
    public static final String EDIT_PROFILE = BuildConfig.BASE_URL + "editprofile";

    public static final String GET_CERTIFICATION = BuildConfig.BASE_URL + "getcerts";
    public static final String SAVE_CERTIFICATION = BuildConfig.BASE_URL + "savecert";
    public static final String DELETE_CERTIFICATION = BuildConfig.BASE_URL + "delcert";

    public static final String GET_SKILL = BuildConfig.BASE_URL + "getskills";
    public static final String SAVE_SKILL = BuildConfig.BASE_URL + "saveskill";
    public static final String EDIT_SKILL = BuildConfig.BASE_URL + "editskill";
    public static final String DELETE_SKILL = BuildConfig.BASE_URL + "delskill";

    public static final String GET_WORK_HISTORY = BuildConfig.BASE_URL + "getjobs";
    public static final String SAVE_WORK_HISTORY = BuildConfig.BASE_URL + "savejob";
    public static final String DELETE_WORK_HISTORY = BuildConfig.BASE_URL + "deljob";

    public static final String GET_CUSTOMER_CASE = BuildConfig.BASE_URL + "getccases";
    public static final String GET_EXPERT_CASE = BuildConfig.BASE_URL + "getecases";

    public static final String CHAT_HIST = BuildConfig.BASE_URL + "chathist";
    public static final String SEND_MSG = BuildConfig.BASE_URL + "sendmsg";
    public static final String GET_MSGS = BuildConfig.BASE_URL + "getmsgs";
    public static final String UPLOAD_IMG = BuildConfig.BASE_URL + "uploadimg";

    public static final String MSG_NOTIF = BuildConfig.BASE_URL + "msgnotif";
    public static final String CLEAR_ONE = BuildConfig.BASE_URL + "clearone";
    public static final String CLEAR_ALL = BuildConfig.BASE_URL + "clearall";

    public static final String SERVICE_FEE = BuildConfig.BASE_URL + "servicefee";
    public static final String SAVE_ORDER = BuildConfig.BASE_URL + "saveorder";
    public static final String PAYPAL_PDT = BuildConfig.BASE_URL + "pppdt";
    public static final String CPAYBYORDERID = BuildConfig.BASE_URL + "cpaybyorderid";
    public static final String DOKU_NOTIFY = BuildConfig.BASE_URL + "dokunotify";

    public static final String DOKU_MOBILE = BuildConfig.BASE_URL + "dokumobile";
}
