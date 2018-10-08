package com.radicallabsinc.pakarhero.utils;

import java.util.HashMap;

public final class AppConstants {

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "pakarhero.db";
    public static final String PREF_NAME = "pakarhero_pref";

    public static final long NULL_INDEX = -1L;

    public static final String ERROR_WRONG_NAME_OR_PASSWORD = "wrongUsernameOrPassword";
    public static final String ERROR_INVALID_SESSION = "invalidSession";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static HashMap<String, String> expertiseMap = new HashMap<String,String>();
    public static HashMap<String, String> lovMap = new HashMap<String, String>();
    public static HashMap<String, Double> serviceFeeMap = new HashMap<String, Double>();

    private AppConstants() {
        // This utility class is not publicly instantiable
    }

}
