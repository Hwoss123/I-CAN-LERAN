package com.Service;

import com.pojo.Match_Degree;
import com.pojo.User;

import java.util.List;
import java.util.Map;


public interface MatchService {
       Match_Degree getDegree(String jwt);

       List<User> matching(String jwt, Map<String, List<String>> map);
}
