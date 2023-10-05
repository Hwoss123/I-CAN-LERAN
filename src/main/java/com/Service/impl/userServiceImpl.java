package com.Service.impl;

import com.mapper.UserMapper;
import com.Service.UserService;
import com.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public boolean isAccountExist(String account) {
        User user = userMapper.FindUserByAccount(account);
        return user != null;
    }

    @Override
    public boolean register(User user) {
        String account = user.getAccount();
//        这里需要去判断注册是否手机号存在
        if(isAccountExist(account)){
            return false;
        }else{
            userMapper.register(user);
            return true;
        }
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user)>0;
    }

    @Override
    public User getUserById(Integer user_id) {
        return userMapper.FindUserById(user_id);
    }

    @Override
    public boolean updateAvatar64(Integer user_id, String avatar64) {
        return userMapper.uploadAvatar(user_id, avatar64)>0;
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public List<User> getUsersByMBTI(String mbti) {
        return userMapper.getUsersByMBTI(mbti);
    }

    @Override
    public boolean updatePassword(String account, String password) {
        User user = userMapper.FindUserByAccount(account);
        if(user==null){
            return false;
        }
        userMapper.resetPassword(account,password);
        return true;
    }
}
