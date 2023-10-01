package com.Service;

import com.pojo.Question;
import com.pojo.TestReport;

import java.util.List;
import java.util.Map;

public interface MBTITestService {

    //根据num获取对应题目数的题目
    List<Question> getQuestionsByQNum(Integer type);
    //返回测试结果
    String getTestResult(String jwt, Map<Integer, String> map);

    //获取MBTI测试报告
    TestReport getTestReport(String mbti);
}