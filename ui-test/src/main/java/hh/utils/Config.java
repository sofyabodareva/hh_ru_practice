package hh.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String EMAIL_KEY = "HH_EMAIL";
    private static final String PASSWORD_KEY = "HH_PASSWORD";
    private static final String LOAD_ERROR_MSG = "Не удалось загрузить файл config.properties. Убедитесь, что он существует в корне проекта.";
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException e) {
            throw new RuntimeException(LOAD_ERROR_MSG, e);
        }
    }

    public static String getEmail() {
        return properties.getProperty(EMAIL_KEY);
    }

    public static String getPassword() {
        return properties.getProperty(PASSWORD_KEY);
    }
}
