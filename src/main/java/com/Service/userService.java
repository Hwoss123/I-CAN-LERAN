package com.Service;

import com.pojo.User;

import javax.swing.text.StyledEditorKit;

public interface userService {
//    判断是否有这个用户
    Boolean login(User user);
//    判断注册是否合法
    boolean register(User user);
//    判断是否存在账号存在
    boolean isAccountExist(String account);
//
    boolean updatePassword(String account, String newPassword);
}
