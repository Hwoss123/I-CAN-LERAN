package com.Controller;


import com.Dao.Result;
import com.Service.userService;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private userService userService;


    @PostMapping("/login")
    public Result login(String username, String password, String UUID, String verificationCode) {
        String factCode = CaptchaController.cache.get(UUID);
        long oldTime = CaptchaController.expire.get(UUID);
        long newTime  = System.currentTimeMillis();
        if(newTime-oldTime>12000){
            return Result.error("验证码过期");
        }
        if(!factCode.equals(verificationCode)) {
            return Result.error("验证码错误");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(userService.login(user)){
            return Result.success();
        }
            return Result.error("登录失败");
    }
    @PostMapping("/register")
    public Result register(User user,String UUID,String code){
        String factCode = CaptchaController.cache.get(UUID);
        long oldTime = CaptchaController.expire.get(UUID);
        long newTime  = System.currentTimeMillis();
        if(newTime-oldTime>12000){
            return Result.error("验证码过期");
        }
        if( factCode.equals(code)){
            return Result.error("验证码错误");
        }
        if(!isAtLeastEightCharacters(user.getPassword())) {
            return Result.error("密码长度至少8位");
        }
        if(isElevenDigits(user.getAccount())){
            return Result.error("输入的手机号格式错误");
        }
       if(userService.register(user)){
           return Result.success();
       }
       else {
           return Result.error("注册失败，当前已经有账号");
       }
    }

    public Result resetPassword(String account, String password){
        if(!isAtLeastEightCharacters(password)) {
            return Result.error("密码长度至少8位");
        }
        if(userService.updatePassword(account, password)){
            return Result.success();
        }else {
            return Result.error("账号错误");
        }
    }
    public boolean isElevenDigits(String str) {
        // 使用正则表达式匹配11位数字
        String regex = "^\\d{11}$";
        return Pattern.matches(regex, str);
    }
    public static boolean isAtLeastEightCharacters(String str) {
            // 使用正则表达式匹配至少8位字符
            String regex = "^.{8,}$";
            return Pattern.matches(regex, str);
        }
    }
