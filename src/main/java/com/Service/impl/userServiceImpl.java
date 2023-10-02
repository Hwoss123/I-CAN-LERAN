package com.Service.impl;

import com.Mapper.UserMapper;
import com.Service.userService;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private UserMapper UserMapper;

    @Override
    public boolean isAccountExist(String account) {
        User user = UserMapper.FindUserByAccount(account);
        return user != null;
    }

    @Override
    public boolean register(User user) {
       String account = user.getAccount();
//        这里需要去判断注册是否手机号存在
       if(isAccountExist(account)){
            return false;
       }else{
           UserMapper.register(user);
           return true;
       }
    }

    @Override
    public boolean updateUser(User user) {
        return UserMapper.updateUser(user)>0;
    }

    @Override
    public User getUserById(Integer user_id) {
        return UserMapper.FindUserById(user_id);
    }

    @Override
    public boolean updateAvatar64(Integer user_id, String avatar64) {
      return UserMapper.uploadAvatar(user_id, avatar64)>0;
    }

    @Override
    public Boolean login(User user) {
        User user1 = UserMapper.login(user);
        return user1 != null;
    }

    @Override
    public boolean updatePassword(String account, String password) {
        User user = UserMapper.FindUserByAccount(account);
        if(user==null){
            return false;
        }
        UserMapper.resetPassword(account,password);
        return true;
    }
}
