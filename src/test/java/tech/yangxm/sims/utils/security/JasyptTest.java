package tech.yangxm.sims.utils.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

public class JasyptTest {

    @Test
    public void testEncode() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("");
        //要加密的数据
        String plaintext = "";
        String ciphertext = textEncryptor.encrypt(plaintext);
        System.out.println(plaintext + " : " + ciphertext);
    }
}
