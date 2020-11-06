package tech.yangxm.sims.utils.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordUtilTest {

    @Autowired
    private PasswordUtil passwordUtil;

    @Test
    public void testEncryptAndDecrypt() throws Exception {
        String plaintext1 = "yangxianming1996";
        log.info("plaintext1: " + plaintext1);

        String ciphertext = passwordUtil.encrypt(plaintext1);
        log.info("ciphertext: " + ciphertext);

        String plaintext2 = passwordUtil.decrypt(ciphertext);
        log.info("plaintext2: " + plaintext2);

        Assert.assertEquals(plaintext1, plaintext2);
    }
}
