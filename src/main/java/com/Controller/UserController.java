package com.Controller;


import com.Service.userService;
import com.pojo.Result;
import com.pojo.User;
import com.pojo.VerticalUser;
import com.utils.Code;
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
        return userService.login(user) ? Result.success(Code.LOGIN_OK)
                : Result.error(Code.LOGIN_ERR,"登录失败");

    }
    @PostMapping("/register")
    public Result register(@RequestBody VerticalUser verticaluser){
        String UUID = verticaluser.getUUID();
        String code = verticaluser.getCode();
        User user = verticaluser.getUser();
        String factCode = CaptchaController.cache.get(UUID);
        if(factCode==null){
            return Result.error(Code.REGISTER_ERR,"请输入验证码");
        }
        Long oldTime = CaptchaController.expire.get(UUID);
        if(oldTime==null){
                oldTime = System.currentTimeMillis();
        }
        long newTime  = System.currentTimeMillis();
        if(newTime-oldTime>12000){
            return Result.error(Code.VERTICAL_LOGIN_ERR,"验证码过期");
        }
        else if(!(factCode.equals(code))){
            return Result.error(Code.VERTICAL_LOGIN_ERR,"验证码错误");
        }
        else if(!isAtLeastEightCharacters(user.getPassword())) {
            return Result.error(Code.REGISTER_ERR,"密码至少8位");
        }
        else if(!(isElevenDigits(user.getAccount()))){
            return Result.error(Code.REGISTER_ERR,"手机号格式错误");
        }
        return userService.register(user) ? Result.success(Code.REGISTER_OK) :
                Result.error(Code.REGISTER_ERR,"已存在账号");

    }
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody User user) {
        String password = user.getPassword();
        String account  = user.getAccount();
        if(!isAtLeastEightCharacters(password)) {
            return Result.error(Code.LOGIN_ERR,"密码至少8位");
        }
        return userService.updatePassword(account, password) ?
                Result.success(Code.RESET_PASSWORD_OK)
                : Result.error(Code.RESET_PASSWORD_ERR,"账号错误");

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
