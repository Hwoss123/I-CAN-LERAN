package com.Controller;


import com.Service.userService;
import com.pojo.Result;
import com.pojo.User;
import com.pojo.VerticalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private userService userService;


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if(userService.login(user)){
            return Result.success();
        }
            return Result.error("登录失败");
    }
    @PostMapping("/register")
    public Result register(@RequestBody VerticalUser verticaluser){
        String UUID = verticaluser.getUUID();
        String code = verticaluser.getCode();
        User user = verticaluser.getUser();
        String factCode = CaptchaController.cache.get(UUID);
        if(factCode==null){
            return Result.error("请输入验证码");
        }
        Long oldTime = CaptchaController.expire.get(UUID);
        if(oldTime==null){
                oldTime = System.currentTimeMillis();
        }
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
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody User user) {
        String password = user.getPassword();
        String account  = user.getAccount();
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
