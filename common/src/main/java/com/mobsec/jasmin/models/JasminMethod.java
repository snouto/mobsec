package com.mobsec.jasmin.models;

import com.mobsec.jasmin.api.SignatureAlgorithm;
import com.mobsec.jasmin.api.SignatureGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snouto on 12/03/2015.
 */
public class JasminMethod implements SignatureGenerator{

    private String methodName;
    private List<String> parameters;
    private boolean constructor;
    private int referenceCount;
    private String methodNameWithParameters;
    private SignatureAlgorithm algorithm;
    private List<MethodSpec> methodSpecs;


    public JasminMethod(){

        this.parameters = new ArrayList<String>();
        this.setMethodSpecs(new ArrayList<MethodSpec>());

    }

    public JasminMethod(SignatureAlgorithm algorithm)
    {
        this();
        this.setAlgorithm(algorithm);
    }


    @Override
    public String toString() {

        String output =  String.format("Method Name : %s , Is Constructor : %s, Parameters Number : %s",this.getMethodNameWithParameters(),this.isConstructor(),this.getParameters().size());

        output += ", (";

        for(String param : this.getParameters())
        {
            output += param;
            output += " || ";
        }

        output +=")";

        output += "\r\nInvoked Methods\r\n";

        for(MethodSpec spec : this.getMethodSpecs())
        {
            output += spec.toString() + "\r\n";
        }


        return output;


    }

    public JasminMethod(boolean constructor)
    {
        this();
        this.setConstructor(constructor);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public boolean isConstructor() {
        return constructor;
    }

    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
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

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<MethodSpec> getMethodSpecs() {
        return methodSpecs;
    }

    public void setMethodSpecs(List<MethodSpec> methodSpecs) {
        this.methodSpecs = methodSpecs;
    }

    public String getMethodNameWithParameters() {
        return methodNameWithParameters;
    }

    public void setMethodNameWithParameters(String methodNameWithParameters) {
        this.methodNameWithParameters = methodNameWithParameters;
    }
}
