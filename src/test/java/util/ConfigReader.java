package util;

import java.io.*;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader(String propertyFilePath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }



    public String getProperty(String name) {
        String result = System.getProperty(name);
        if (result != null) {
            return result;
        }
        result = properties.getProperty(name);
        if (result != null) {
            return result;
        }
        throw new IllegalStateException("Required property is absent: " + name);
    }


    public String getProperty(String name, String defaultValue) {
        try {
            return getProperty(name);
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }

}
