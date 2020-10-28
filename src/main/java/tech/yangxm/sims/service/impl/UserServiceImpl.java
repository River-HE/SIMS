package tech.yangxm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yangxm.sims.mapper.UserMapper;
import tech.yangxm.sims.pojo.User;
import tech.yangxm.sims.service.UserService;
import tech.yangxm.sims.utils.security.VerifyCodeManager;

@Slf4j
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private VerifyCodeManager verifyCodeManager;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void sendVerifyCode(String username) {
        verifyCodeManager.generateCode(username);
    }

    @Override
    public boolean loginByVerifyCode(String username, String code) {
        User user = userMapper.selectById(username);
        if (null == user) {
            log.info("没有该用户：" + username);
            return false;
        }
        return verifyCodeManager.validate(username, code);
    }
}
