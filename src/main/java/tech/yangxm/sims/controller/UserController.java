package tech.yangxm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yangxm.sims.model.UserModel;
import tech.yangxm.sims.pojo.User;
import tech.yangxm.sims.service.UserService;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/verify_code")
    public void sendVerifyCode(@RequestParam("username") String username) {
        userService.sendVerifyCode(username);
    }

    @PostMapping("/login/code")
    public String loginByVerifyCode(@RequestParam("username") String username,
                                    @RequestParam("code") String code) {
        boolean res = userService.loginByVerifyCode(username, code);
        if (res) {
            return "登录成功！";
        } else {
            return "登录失败！";
        }
    }

    @PostMapping("/login/pwd")
    public String loginByPassword(@RequestParam("username") String username,
                                  @RequestParam("pwd") String pwd) {
        boolean res = userService.loginByPassword(username, pwd);
        if (res) {
            return "登录成功！";
        } else {
            return "登录失败！";
        }
    }

    @PostMapping("/registration")
    public String toRegister(@RequestBody UserModel um) {
        boolean res = userService.toRegister(um.toUser());
        if (res) {
            return "注册成功！";
        } else {
            return "注册失败！";
        }
    }
}
