package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:panchal@yahoo-inc.com">Deven Panchal</a>
 */
public final class StreamUtils {

    public static List<String> loadResourceAsList(ClassLoader classLoader, String resourceName) {
        List<String> list = new ArrayList<>();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourceName)) {
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException("Error loading resource.");
        }
    }

}
