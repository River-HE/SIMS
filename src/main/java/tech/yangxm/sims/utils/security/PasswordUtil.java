package tech.yangxm.sims.utils.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private BasicTextEncryptor textEncryptor = null;

    @Value("${user.password.key}")
    private String userPasswordKey;

    private BasicTextEncryptor getTextEncryptor() {
        if (null == textEncryptor) {
            textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(userPasswordKey);
        }
        return textEncryptor;
    }

    public String encrypt(String plaintext) {
        return getTextEncryptor().encrypt(plaintext);
    }

    public String decrypt(String ciphertext) {
        return getTextEncryptor().decrypt(ciphertext);
    }
}
