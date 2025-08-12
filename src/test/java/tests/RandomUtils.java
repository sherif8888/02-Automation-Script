package tests;

import java.util.UUID;

public class RandomUtils {
    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return "user" + uuid.substring(0, 8) + "@example.com";
    }
}
