package com.mobsec.algorithm;

import com.mobsec.jasmin.api.SignatureAlgorithm;

import java.security.MessageDigest;

/**
 * Created by snouto on 12/03/2015.
 */
public class MD5Algorithm implements SignatureAlgorithm {


    private String payload;

    public MD5Algorithm(String text)
    {
        this.setPayload(text);
    }

    public MD5Algorithm(){

    }

    public void setPayload(String text)
    {
        this.payload = text;
    }


    @Override
    public String hash() throws  Exception{

        return this.encrypt(this.getPayload());
    }

    private String encrypt(String payload) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(payload.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

    public String getPayload() {
        return payload;
    }
}
