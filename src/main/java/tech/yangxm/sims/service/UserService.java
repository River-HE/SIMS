package tech.yangxm.sims.service;

public interface UserService {

    void sendVerifyCode(String username);

    boolean loginByVerifyCode(String username, String code);
}
