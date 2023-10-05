package com.service.impl;

import com.mapper.MatchMapper;
import com.mapper.UserMapper;
import com.service.MatchService;
import com.service.UserService;
import com.pojo.Match_Degree;
import com.pojo.User;
import com.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private UserService userService;
    @Override
    public List<User> matching(String jwt,Map<String, List<String>> map) {
        List<String> list = new ArrayList<String>();

        List<User> returnList = new ArrayList<User>();
        List<User> userList = userService.getUsers();
        if (map==null){
//     不传值默认返回随机5
            Collections.shuffle(userList);
            returnList.addAll(userList);
            returnList = new ArrayList<>(userList.subList(0,5));
        }
        else {
//            传值
//                returnList = new ArrayList<>(userList.subList(0,5));
                if(map.containsKey("MBTI")){
                    list = map.get("MBTI");
                }
//            获取选择后筛选出只包含选择的集合userList，获取所有userList后进行操作
                for (String mbti : list) {
//                分别去获取对应的用户加上去再打乱
                    List<User> tempUserList  = userService.getUsersByMBTI(mbti);
                    returnList.addAll(tempUserList);
                }
                Collections.shuffle(returnList);
                returnList = new ArrayList<>(returnList.subList(0,5));
            }
//               这里如果要保证刷新的五个不会重复以及匹配到的需要和后面的重复，思路是查询当前id的好友和等待列表然后去掉对应（待完善）
        return returnList;
    }

    @Override
    public Match_Degree getDegree(String jwt) {
//        通过id去获取对应的对象的mbti
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer user_id = (Integer) claims.get("id");
       User user=  userMapper.FindUserById(user_id);
       String mbti = user.getMbti();
       mbti = mbti.toUpperCase();
//       mbti的各种匹配度
        //        注意这里degree是包含第一个mbti类型的
        return matchMapper.getDegreeByMBTI(mbti);
    }
}
