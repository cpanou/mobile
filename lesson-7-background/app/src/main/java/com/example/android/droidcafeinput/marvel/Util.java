package com.example.android.droidcafeinput.marvel;

import android.support.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    private static final String MARVEL_PUBLIC_KEY = "f4f60bbba0eb44b0a8ae7b22b70eada6";
    private static final String MARVEL_PRIVATE_KEY = "4f0a1bf779e40df51c8009217fffc104330e1ff7";

    public static String getAuthWithMD5() {
        try {
            Long timestamp = System.currentTimeMillis();
            StringBuilder hexString = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(String.valueOf(timestamp).getBytes());
            md.update(MARVEL_PRIVATE_KEY.getBytes());
            md.update(MARVEL_PUBLIC_KEY.getBytes());
            byte[] hash = md.digest();
            for (byte b : hash) {
                if ((0xff & b) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & b)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & b));
                }
            }
            return "ts=" + timestamp + "&apikey="+ MARVEL_PUBLIC_KEY + "&hash=" + hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "ts=1605656906&apikey=f4f60bbba0eb44b0a8ae7b22b70eada6&hash=2160259a947a668a2ad6db817b4f6e8c";
        }
    }

}
