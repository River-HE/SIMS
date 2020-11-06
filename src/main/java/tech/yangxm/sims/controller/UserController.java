package tech.yangxm.sims.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yangxm.sims.model.UserModel;
import tech.yangxm.sims.service.UserService;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/verify_code")
    public void sendVerifyCode(@RequestParam("username") String username) {
        userService.sendVerifyCode(username);
    }

    @PostMapping("/login/code")
    public String loginByVerifyCode(@RequestParam("username") String username,
                                    @RequestParam("code") String code) throws Exception {
        return userService.loginByVerifyCode(username, code);
    }

    @PostMapping("/login/pwd")
    public String loginByPassword(@RequestParam("username") String username,
                                  @RequestParam("pwd") String pwd) throws Exception {
        return userService.loginByPassword(username, pwd);
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
