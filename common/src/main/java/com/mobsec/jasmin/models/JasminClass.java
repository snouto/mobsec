package com.mobsec.jasmin.models;

import com.mobsec.jasmin.api.SignatureAlgorithm;
import com.mobsec.jasmin.api.SignatureGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snouto on 12/03/2015.
 */
public class JasminClass implements SignatureGenerator {

    private String className;
    private String canonicalName;
    private String superClassName;
    private String superCanonicalName;
    private SignatureAlgorithm algorithm;
    private List<JasminMethod> methods;
    private int referenceCount;

    public JasminClass()
    {
        this.setMethods(new ArrayList<JasminMethod>());
    }


    public JasminClass(SignatureAlgorithm algorithm)
    {
        this();
        this.setSignatureAlgorithm(algorithm);
    }


    @Override
    public String toString() {

        String output =  String.format("Class Name : %s , Canonical Name : %s , Methods Count : %s",this.getClassName(),this.getCanonicalName()
        ,this.getMethods().size());

        output += "\r\n";

        for(JasminMethod method : this.getMethods())
        {
            output += method.toString()+"\r\n";
        }

        return output;


    }

    @Override
    public String generateSignature() {

        return null;
    }

    @Override
    public void setSignatureAlgorithm(SignatureAlgorithm algorithm) {

        this.setSignatureAlgorithm(algorithm);

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public String getSuperCanonicalName() {
        return superCanonicalName;
    }

    public void setSuperCanonicalName(String superCanonicalName) {
        this.superCanonicalName = superCanonicalName;
    }

    public List<JasminMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<JasminMethod> methods) {
        this.methods = methods;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }
}
