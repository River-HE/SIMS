package tech.yangxm.sims.utils.algorithm;

import java.util.Random;

public class VerifyCodeUtil {

    private static final char[] CHARS = "0123456789".toCharArray();

    public static String generateCode(int length) {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < length; i++) {
            char c = CHARS[random.nextInt(CHARS.length)];
            code += c;
        }
        return code;
    }
}
