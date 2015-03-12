package com.mobsec.engine;

import com.mobsec.jasmin.JasminReaderImpl;
import com.mobsec.jasmin.api.SignatureAlgorithm;
import com.mobsec.jasmin.models.JasminApplication;
import com.mobsec.wrapper.ApplicationWrapper;

import java.io.File;

/**
 * Created by snouto on 12/03/2015.
 */
public class MobSecEngine {


    private String malwareFolder;

    private SignatureAlgorithm algorithm;

    public MobSecEngine(String folder,SignatureAlgorithm algorithm)
    {
        this.setMalwareFolder(folder);
        this.setAlgorithm(algorithm);
    }

    public String encode()
    {
        try
        {
            JasminApplication app = new JasminApplication(this.getAlgorithm());
            this.readApp(app);

            ApplicationWrapper wrapper = new ApplicationWrapper(app);

            return wrapper.encode();

        }catch(Exception s)
        {
            s.printStackTrace();
            return null;
        }
    }


    private void readApp(JasminApplication app) throws Exception
    {
        File parentFolder = new File(this.getMalwareFolder());

        File[] nestedFiles = parentFolder.listFiles();

        for(File nested : nestedFiles)
        {
            this.readFile(nested,app);
        }

    }

    private void readFile(File file,JasminApplication app)
    {
        if(!file.isDirectory())
        {
            JasminReaderImpl reader = new JasminReaderImpl(file.getAbsolutePath());
            app.getClasses().add(reader.parseClass());

        }else
        {
            File[] nestedFiles = file.listFiles();

            for(File nested : nestedFiles)
            {
                this.readFile(nested,app);
            }
        }
    }


    public String getMalwareFolder() {
        return malwareFolder;
    }

    public void setMalwareFolder(String malwareFolder) {
        this.malwareFolder = malwareFolder;
    }

    public SignatureAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
