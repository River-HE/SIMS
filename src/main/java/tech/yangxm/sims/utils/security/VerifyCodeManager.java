package tech.yangxm.sims.utils.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class VerifyCodeManager {

    @Autowired
    private MailService mailService;

    private Map<String, VerifyCode> userCodeMap = new HashMap<>();

    public void generateCode(String username) {
        VerifyCode verifyCode = new VerifyCode();

        userCodeMap.put(username, verifyCode);
        sendEmail(username, verifyCode.getCode());
    }

    public boolean validate(String username, String code) {
        if (!userCodeMap.containsKey(username)) {
            log.info("没有这个记录：" + username);
            return false;
        }

        VerifyCode verifyCode = userCodeMap.get(username);
        return verifyCode.validate(code);
    }

    private void sendEmail(String username, String code) {
        log.info("username: " + username + " code: " + code);
        String subject = "VERIFY CODE";
        mailService.sendSimpleMail(username, subject, code);
    }
}
