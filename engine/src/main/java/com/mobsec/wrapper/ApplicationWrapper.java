package com.mobsec.wrapper;

import com.mobsec.algorithm.MD5Algorithm;
import com.mobsec.enumerator.OpCallsEnumerator;
import com.mobsec.jasmin.models.JasminApplication;
import com.mobsec.jasmin.models.JasminClass;
import com.mobsec.jasmin.models.JasminMethod;
import com.mobsec.jasmin.models.MethodSpec;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snouto on 12/03/2015.
 */
public class ApplicationWrapper {

    private static final int NOT_FOUND = -1;
    private JasminApplication app;

    private Map<String,Double> systemCalls;

    public ApplicationWrapper(JasminApplication app)
    {
        this.setApp(app);
    }

    private String getHexValue(String key)
    {
        if(this.systemCalls.containsKey(key))
            return Double.toString(this.systemCalls.get(key));
        else return null;
    }

    public void prepareWrapper()
    {
        try
        {

            OpCallsEnumerator enumerator = new OpCallsEnumerator();

            this.systemCalls = enumerator.loadSystemCalls("C:\\Users\\snouto\\Desktop\\infosec\\systemCalls.json");


        }catch(Exception s)
        {
            s.printStackTrace();
        }
    }



    public String encode() throws Exception
    {
        if(this.systemCalls == null)
            this.prepareWrapper();

        Map<String,Integer> referenceCount = this.prepareAppCode();

        StringBuilder appBuilder = new StringBuilder();

        for(JasminClass jasminClass : getApp().getClasses())
        {
            StringBuilder classBuilder = new StringBuilder();

            for(JasminMethod jasminMethod : jasminClass.getMethods())
            {
                StringBuilder hexBuilder = new StringBuilder();

                for(MethodSpec methodCall : jasminMethod.getMethodSpecs())
                {
                    String val = this.getHexValue(methodCall.getInvokedMethod());
                    if(val != null)
                        hexBuilder.append(val).append(" ");
                }

                    if(hexBuilder.toString() != null && hexBuilder.length() > 0
                            && referenceCount.get(jasminMethod.getMethodNameWithParameters()) > 0)
                        classBuilder.append(hexBuilder.toString()).append(" ");

            }

                if(classBuilder.toString() != null && classBuilder.toString().length() > 0)
                    appBuilder.append(classBuilder.toString()).append(" ");

        }

       //get the MD5 Algorithm
        MD5Algorithm algorithm = new MD5Algorithm();
        algorithm.setPayload(appBuilder.toString());
        //System.out.println(appBuilder.toString());
        return algorithm.hash();


    }

    private Map<String,Integer> prepareAppCode()
    {
        Map<String,Integer> referenceCount = new HashMap<String, Integer>();

        for(JasminClass jasminClass : getApp().getClasses())
        {
            this.addReference(referenceCount,jasminClass.getClassName());

            for(JasminMethod jasminMethod : jasminClass.getMethods())
            {
                this.addReference(referenceCount,jasminMethod.getMethodNameWithParameters());

                for(MethodSpec spec : jasminMethod.getMethodSpecs())
                {
                    this.addReference(referenceCount,spec.getInvokedMethod());
                }
            }
        }

        return referenceCount;
    }

    private void addReference(Map<String,Integer> referenceCount , String key)
    {
        if(referenceCount.containsKey(key))
        {
            int count = referenceCount.get(key);
            ++count;
            referenceCount.remove(key);
            referenceCount.put(key,count);
        }else
            referenceCount.put(key,0);
    }

    public JasminApplication getApp() {
        return app;
    }

    public void setApp(JasminApplication app) {
        this.app = app;
    }
}
