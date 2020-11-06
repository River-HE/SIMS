package tech.yangxm.sims.service;

import tech.yangxm.sims.pojo.User;

public interface UserService {

    void sendVerifyCode(String username);

    String loginByVerifyCode(String username, String code) throws Exception;

    String loginByPassword(String username, String pwd) throws Exception;

    boolean toRegister(User user);

    boolean findByUsername(String username);
}
