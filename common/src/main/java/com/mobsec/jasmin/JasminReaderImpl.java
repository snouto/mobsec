package com.mobsec.jasmin;

import com.mobsec.jasmin.api.JasminReader;
import com.mobsec.jasmin.models.JasminClass;
import com.mobsec.jasmin.models.JasminMethod;
import com.mobsec.jasmin.models.MethodSpec;

import java.io.*;

/**
 * Created by snouto on 12/03/2015.
 */
public class JasminReaderImpl implements JasminReader {

    private String jasminFile;

    public JasminReaderImpl(String file)
    {
        this.setJasminFile(file);
    }


    public JasminClass parseClass()
    {
        JasminClass jasminClass = new JasminClass();

        try
        {
            File classFile = new File(this.getJasminFile());

            FileInputStream is = new FileInputStream(classFile);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = null;

            while((line = reader.readLine()) != null)
            {
                //Parse the current line

                this.parseLine(line,jasminClass,reader);

            }


            return jasminClass;


        }catch(Exception s)
        {
            s.printStackTrace();
            return null;
        }
    }

    private void parseLine(String line, JasminClass jasminClass,BufferedReader reader) {

        //parse the current line
        if(line.startsWith(JasminConstants.CLASS))
        {
           //It means it is a class
           //Split the current file
            String[] splittedString = line.split(" ");

            if(splittedString != null && splittedString.length > 0)
            {
                //take the second part
                String className = splittedString[splittedString.length - 1];
                String canonicalName = className.replace("/",".");

                jasminClass.setClassName(className);
                jasminClass.setCanonicalName(canonicalName);
            }

        }else if (line.startsWith(JasminConstants.METHOD))
        {
            //it means it is a method
            String[] methodSplitted = line.split(" ");

            //take the last one
            if(methodSplitted != null && methodSplitted.length > 0)
            {
                //take the method name , which is the last part of that array
                String name = methodSplitted[methodSplitted.length - 1];
                int openingbraces = name.indexOf('(');
                int start = name.indexOf(name.charAt(0));
                String methodName = name.substring(start,openingbraces);
                boolean constructor = (methodName.equals("<init>"));
                JasminMethod method = new JasminMethod(constructor);
                method.setMethodNameWithParameters(jasminClass.getClassName() +"/" + name);
                method.setMethodName(jasminClass.getClassName() +"/" + methodName);
                int closingbraces = name.indexOf(')');
                String parameters = name.substring(openingbraces,closingbraces);
                //parameters (Landroid/app/PendingIntent;Landroid/os/Parcel;)
                String[] splittedParams = parameters.split(";");

                for(String param : splittedParams)
                {
                    if(param != null && param.length() > 0 && !param.equals(""))
                    {
                        method.getParameters().add(param);
                    }
                }

                this.readMethodSpecs(method,reader);
                jasminClass.getMethods().add(method);
            }


        }

    }

    private void readMethodSpecs(JasminMethod method, BufferedReader reader) {

        try
        {

            String line = null;

            while(!((line = reader.readLine()).startsWith(JasminConstants.METHOD_END)))
            {
                if(line.startsWith(JasminConstants.METHOD_INVOKE_VIRTUAL) || line.startsWith(JasminConstants.METHOD_INVOKE_STATIC)
                        || line.startsWith(JasminConstants.METHOD_INVOKE_NONVIRTUAL) ||
                        line.startsWith(JasminConstants.METHOD_INVOKE_SPECIAL) ||
                        line.startsWith(JasminConstants.METHOD_INVOKE_NEW))
                {
                    String[] splittedSpec= line.split(" ");

                    method.getMethodSpecs().add(new MethodSpec(splittedSpec[1]));


                }
            }

        }catch(Exception s)
        {

        }
    }


    public String getJasminFile() {
        return jasminFile;
    }

    public void setJasminFile(String jasminFile) {
        this.jasminFile = jasminFile;
    }
}
