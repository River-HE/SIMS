package tech.yangxm.sims.utils.security;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import tech.yangxm.sims.utils.algorithm.VerifyCodeUtil;

@Slf4j
public class VerifyCode {

    private static final int EXPIRATION_TIME_MIN = 10;

    private static final int CODE_LENGTH = 6;

    @Getter
    private String code;

    private long initTime;

    public VerifyCode() {
        code = VerifyCodeUtil.generateCode(CODE_LENGTH);
        initTime = System.currentTimeMillis();
    }

    public boolean validate(String code) {
        if (null == code) {
            log.info("验证码为null");
            return false;
        }

        if (!this.code.equals(code)) {
            log.info("验证码匹配不成功，正确的是：" + this.code + " 输入的是：" + code);
            return false;
        }

        long currentTime = System.currentTimeMillis();
        long expireTime = initTime + EXPIRATION_TIME_MIN * 60 * 1000;
        if (currentTime <= expireTime) {
            return true;
        } else {
            log.info("验证码过期");
            return false;
        }
    }
}
