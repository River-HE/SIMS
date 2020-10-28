package tech.yangxm.sims.utils.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class VerifyCodeUtilTest {

    @Test
    public void testGenerateCode() {
        log.info(VerifyCodeUtil.generateCode(6));
        log.info(VerifyCodeUtil.generateCode(6));
        log.info(VerifyCodeUtil.generateCode(8));
        log.info(VerifyCodeUtil.generateCode(8));
        log.info(VerifyCodeUtil.generateCode(12));
    }
}
