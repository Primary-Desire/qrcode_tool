package util;

import java.security.SecureRandom;

public class CommonUtils {

    public static String randomString(int len) {
        return randomString(len, true);
    }

    public static String randomString(int len, boolean number) {
        return randomString(len, number, false);
    }

    public static String randomString(int len, boolean number, boolean capital) {
        String src = "abcdefghijklmnopqrstuvwxyz1234567890";
        SecureRandom secureRandom = new SecureRandom();
        char[] chars = src.toCharArray();

        StringBuilder result = new StringBuilder();

        int i = 0;
        do {
            char c = chars[secureRandom.nextInt(chars.length)];
            while (!number && Character.isDigit(c)) {
                c = chars[secureRandom.nextInt(chars.length)];
            }
            if (capital) {
                c = Character.toUpperCase(c);
            } else {
                if (secureRandom.nextBoolean()) {
                    c = Character.toUpperCase(c);
                }
            }
            result.append(c);
            i ++;
        } while (i < len);

        return result.toString();
    }


}
