package br.com.cristiano.marvelheroes.utils;

import android.support.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    @NonNull
    public static final String md5(String value){

        MessageDigest md5Encoder = null;
        try {
            md5Encoder = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5Encoder.digest(value.getBytes());
        StringBuilder md5 = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; ++i) {
            md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return md5.toString();
    }


}