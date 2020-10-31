package tech.yangxm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yangxm.sims.mapper.UserMapper;
import tech.yangxm.sims.pojo.User;
import tech.yangxm.sims.service.UserService;
import tech.yangxm.sims.utils.security.PasswordUtil;
import tech.yangxm.sims.utils.security.VerifyCodeManager;

@Slf4j
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private VerifyCodeManager verifyCodeManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtil passwordUtil;

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

    @Override
    public boolean loginByPassword(String username, String pwd) {
        User user = userMapper.selectById(username);
        if (null == user) {
            return false;
        }
        user.setPassword(passwordUtil.decrypt(user.getPassword()));
        if (username.equals(user.getUsername()) && pwd.equals(user.getPassword())) {
            if (!"NORMAL".equals(user.getStatus()) || user.getPwdErrorTimes() >= 5) {
                return false;
            }
            user.setPwdErrorTimes(0);
            return true;
        } else {
            user.setPwdErrorTimes(user.getPwdErrorTimes() + 1);
            if (user.getPwdErrorTimes() == 5) {
                user.setStatus(User.Status.FREEZE);
            }
            return false;
        }
    }

    @Override
    public boolean toRegister(User user) {
        if (null != userMapper.selectById(user.getUsername())) {
            return false;
        }
        user.setPassword(passwordUtil.encrypt(user.getPassword()));
        userMapper.insert(user);
        return true;
    }
}
