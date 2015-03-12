package com.mobsec;

import com.mobsec.algorithm.MD5Algorithm;
import com.mobsec.engine.MobSecEngine;

/**
 * Created by snouto on 12/03/2015.
 */
public class EngineLauncher {

    public static void main(String... args)
    {
        MobSecEngine engine = new MobSecEngine("C:\\Users\\snouto\\Desktop\\infosec\\malwares\\output\\jasmin",new MD5Algorithm());
        System.out.println(engine.encode());

    }
}
