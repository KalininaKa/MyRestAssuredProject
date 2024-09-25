package util;


public class Configuration {

    private static final String PROPS_FILE = "config.properties";
    private static final ConfigReader PROPS;
    public static final String STAND;
    public static final String BASE_URL;


    static {


        PROPS = new ConfigReader(PROPS_FILE);
        STAND = PROPS.getProperty("host", "stand1");
        BASE_URL = PROPS.getProperty(STAND + ".host");

    }
}
