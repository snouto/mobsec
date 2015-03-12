package com.mobsec.jasmin.models;

import java.io.Serializable;

/**
 * Created by snouto on 12/03/2015.
 */
public class MethodSpec implements Serializable {

    private String invokedMethod;

    public MethodSpec(String invoked)
    {
        this.setInvokedMethod(invoked);
    }


    @Override
    public String toString() {

        return this.getInvokedMethod();
    }

    public String getInvokedMethod() {
        return invokedMethod;
    }

    public void setInvokedMethod(String invokedMethod) {
        this.invokedMethod = invokedMethod;
    }
}
