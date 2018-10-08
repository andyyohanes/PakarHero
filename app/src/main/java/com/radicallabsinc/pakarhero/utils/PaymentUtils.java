package com.radicallabsinc.pakarhero.utils;

import android.content.Context;
import android.provider.Settings;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

public class PaymentUtils {


    public static String SHA1(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }


    public static int digitRandomNo(int digits) {
        int max = (int) Math.pow(10, (digits)) - 1; //for digits =7, max will be 9999999
        int min = (int) Math.pow(10, digits - 1); //for digits = 7, min will be 1000000
        int range = max - min; //This is 8999999
        Random r = new Random();
        int x = r.nextInt(range);// This will generate random integers in range 0 - 8999999
        int nDigitRandomNo = x + min; //Our random rumber will be any random number x + min
        return nDigitRandomNo;
    }

    public static String formatPrice(String price) {
        return price.endsWith(".00") ? price : price + ".00";
    }

    private static String getCurrentDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public synchronized static String getDeviceId(Context context) {
        String deviceId = getCurrentDeviceId(context);
        String uuId = null;
        try {
            uuId = UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")).toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uuId;
    }
}