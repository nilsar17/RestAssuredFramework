package org.restTestFramework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagers {

    private static ConfigManagers managers;
    private static final Properties prop = new Properties();

    private ConfigManagers() throws IOException {
        File configFile = new File("./resources/config.properties");
        FileInputStream fis = new FileInputStream(configFile);
        prop. load(fis);
    }

    public static ConfigManagers getInstance(){
        if(managers == null){
            synchronized (ConfigManagers.class){
                try{
                    managers = new ConfigManagers();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return managers;
    }

    public String getProp(String key){
        return System.getProperty(key, prop.getProperty(key));
    }
}
