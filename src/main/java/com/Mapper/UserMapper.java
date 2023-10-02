package com.Mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
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
    @Update("UPDATE user SET password = #{newPassword} WHERE account = #{account}")
    int resetPassword(String account,String newPassword);
    @Update("UPDATE user SET avatar = #{avatar64} WHERE id = #{id}")
    int uploadAvatar(Integer id,String avatar64);
//更新整个User
    @Update("UPDATE user SET username = #{username},job = #{job},mbti = #{mbti} ,interest = #{interest},signature = #{signature},interest_mbti =#{interest_mbti} WHERE id = #{id}")
    int updateUser(User user);
}
