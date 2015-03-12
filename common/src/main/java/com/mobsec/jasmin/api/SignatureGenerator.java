package com.mobsec.jasmin.api;

import java.io.Serializable;

/**
 * Created by snouto on 12/03/2015.
 */
public interface SignatureGenerator extends Serializable {


    public String generateSignature();

    public void setSignatureAlgorithm(SignatureAlgorithm algorithm);
}
