package tech.yangxm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yangxm.sims.mapper.UserMapper;
import tech.yangxm.sims.pojo.User;
import tech.yangxm.sims.service.UserService;
import tech.yangxm.sims.utils.security.JWTManager;
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

    @Autowired
    private JWTManager jwtManager;

    @Override
    public void sendVerifyCode(String username) {
        verifyCodeManager.generateCode(username);
    }

    @Override
    public String loginByVerifyCode(String username, String code) throws Exception {
        User user = userMapper.selectById(username);
        if (null == user) {
            log.info("用户名或验证码错误：" + username);
            return "用户名或验证码错误";
        }
        boolean res = verifyCodeManager.validate(username, code);
        if (res) {
            return jwtManager.createToken(username);
        } else {
            return "验证码无效";
        }
    }

    @Override
    public String loginByPassword(String username, String pwd) throws Exception {
        User user = userMapper.selectById(username);
        if (null == user) {
            return "用户名不存在";
        }
        String tempPwd = passwordUtil.decrypt(user.getPassword());
        if (username.equals(user.getUsername()) && pwd.equals(tempPwd)) {
            if (!User.Status.NORMAL.equals(user.getStatus()) || user.getPwdErrorTimes() >= 5) {
                return "用户不可使用";
            }
            user.setPwdErrorTimes(0);
            return jwtManager.createToken(username);
        } else {
            user.setPwdErrorTimes(user.getPwdErrorTimes() + 1);
            if (user.getPwdErrorTimes() == 5) {
                user.setStatus(User.Status.FREEZE);
            }
            return "用户已被冻结";
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

    @Override
    public boolean findByUsername(String username) {
        if (null != userMapper.selectById(username)) {
            return true;
        } else {
            return false;
        }
    }
}
