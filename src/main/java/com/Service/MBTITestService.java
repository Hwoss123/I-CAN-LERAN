package com.Service;

import com.pojo.Question;

import java.util.List;

public interface MBTITestService {

    List<Question> getQuestionsByQNum(Integer type);
}