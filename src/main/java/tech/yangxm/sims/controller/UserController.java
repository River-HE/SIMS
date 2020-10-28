package tech.yangxm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.yangxm.sims.pojo.User;
import tech.yangxm.sims.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/verify_code")
    public void sendVerifyCode(@RequestParam("username") String username) {
        userService.sendVerifyCode(username);
    }

    @GetMapping("/user/login")
    public String loginByVerifyCode(@RequestParam("username") String username,
                                    @RequestParam("code") String code) {
        boolean res = userService.loginByVerifyCode(username, code);
        if (res) {
            return "登录成功！";
        } else {
            return "登录失败！";
        }
    }
}
