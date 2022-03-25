package core.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class PropsUtils {

    public static String getPayload(String payload) {

        String requestPayload = null;
        String path = String.format("src/test/resources/payloads/%s.json", payload);
        try {
            requestPayload = Files.readString(Path.of(path), StandardCharsets.US_ASCII);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestPayload;
    }
}
