package com.netcompany.timecheck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;

public class PropertiesReader {
    private static PropertyResourceBundle resourceBundle;

    public static String getString(final String key) {
        return getBundle().getString(key);
    }

    private static PropertyResourceBundle getBundle() {
        if (resourceBundle == null) {
            try {
                resourceBundle = new PropertyResourceBundle(Files.newInputStream(Paths.get("dateformats.properties")));
            } catch (IOException e) {
                throw new RuntimeException("Klarte ikke lese properties", e);
            }
        }

        return resourceBundle;
    }

}
