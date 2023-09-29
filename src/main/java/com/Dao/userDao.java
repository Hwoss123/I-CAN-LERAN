package com.Dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface userDao {
//    登录看是否你查询到User
    @Select("select * from user where account = #{account} and password = #{password}")
    User login(User user);
//    注册
    @Insert("INSERT INTO user (account, password) VALUES (#{account},#{password}) ")
    void register(User user);
//    id获取整个对象
    @Select("select * from user where id = #{id}")
    User FindUserById(Integer id);
//    根据账号去获取整个对象
    @Select("select * from user where account = #{account}")
    User FindUserByAccount(String account);
    @Update("UPDATE user SET password = #{newpassword} WHERE account = #{account}")
    int resetPassword(String account,String newPassword);
}
