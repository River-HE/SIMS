package tech.yangxm.sims.service;

import tech.yangxm.sims.pojo.User;

public interface UserService {

    void sendVerifyCode(String username);

    boolean loginByVerifyCode(String username, String code);

    boolean loginByPassword(String username, String pwd);

    boolean toRegister(User user);
}
