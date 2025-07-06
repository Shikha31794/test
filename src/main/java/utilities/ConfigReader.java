package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties props = new Properties();

    // Static block to load the config file once when class is loaded
    static {
        try {
            FileReader reader = new FileReader("src/test/resources/config.properties");
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("❌ Input Exception: Could not load config.properties");
        }
    }

    // Public method to fetch property by key
    public static String get(String key) {
        return props.getProperty(key);
    }
}
