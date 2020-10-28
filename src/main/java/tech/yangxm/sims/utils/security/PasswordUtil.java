package tech.yangxm.sims.utils.security;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;

@Component
public class PasswordUtil {

    //加密算法
    private final String KEY_ALGORITHM = "DES";

    /*
    算法名称/加密模式/填充方式
     */
    private final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    @Value("${user.password.key}")
    private String userPasswordKey;

    /**
     * 生成密钥
     *
     * @return
     */
    private SecretKey keyGenerator() throws Exception {
        byte[] bytes = HexString2Bytes(userPasswordKey);
        DESKeySpec desKey = new DESKeySpec(bytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        return secretKey;
    }

    /**
     * 加密
     *
     * @param plaintext
     * @return
     * @throws Exception
     */
    public String encrypt(String plaintext) throws Exception {
        Key key = keyGenerator();
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecureRandom random = new SecureRandom();
        cipher.init(Cipher.ENCRYPT_MODE, key, random);
        byte[] bytes = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 解密
     *
     * @param ciphertext
     * @return
     */
    public String decrypt(String ciphertext) throws Exception {
        Key key = keyGenerator();
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(ciphertext));
        return new String(bytes, "UTF-8");
    }

    private int parse(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }
        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    private byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }
}
