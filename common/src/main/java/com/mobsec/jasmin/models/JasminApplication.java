package com.mobsec.jasmin.models;

import com.mobsec.jasmin.api.SignatureAlgorithm;
import com.mobsec.jasmin.api.SignatureGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snouto on 12/03/2015.
 */
public class JasminApplication implements SignatureGenerator {

    private String appName;
    private String creatorName;
    private String appVersion;
    private SignatureAlgorithm algorithm;

    private List<JasminClass> classes;

    public JasminApplication(){
        this.setClasses(new ArrayList<JasminClass>());
    }

    public JasminApplication(SignatureAlgorithm algorithm)
    {
        this();
        this.setAlgorithm(algorithm);
    }

    @Override
    public String generateSignature() {
        return null;
    }

    @Override
    public void setSignatureAlgorithm(SignatureAlgorithm algorithm) {

        this.setAlgorithm(algorithm);

    }

    public SignatureAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public List<JasminClass> getClasses() {
        return classes;
    }

    public void setClasses(List<JasminClass> classes) {
        this.classes = classes;
    }
}
