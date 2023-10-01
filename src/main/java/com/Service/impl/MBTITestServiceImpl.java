package com.Service.impl;

import com.Mapper.MBTITestMapper;
import com.Service.MBTITestService;
import com.pojo.*;
import com.utils.ExtractQuestionsUtils;
import com.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class MBTITestServiceImpl implements MBTITestService {

    @Autowired
    private MBTITestMapper mbtiTestMapper;

    @Override
    public List<Question> getQuestionsByQNum(Integer num) {

        List<Question> list =  mbtiTestMapper.selectQuestions();

        List<TypeCount> typeCounts = mbtiTestMapper.selectTypeCount();

        return ExtractQuestionsUtils.extractQuestions(list,typeCounts,num);
    }


    @Override
    public String getTestResult(String jwt, Map<Integer, String> map) {
        TestScore score = new TestScore();
        //解析jwt
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer id = (Integer) claims.get("id");

        score.setUserId(id);

        //获取题库
        List<Question> questions = mbtiTestMapper.selectQuestions();

        //是否可以优化？
        //遍历题库 计算
        for (Question question : questions) {
            if (map.containsKey(question.getId())){
                String value = map.get(question.getId());
                String type = "";
                if ("A".equals(value)){
                    type = question.getAType();
                }else if ("B".equals(value)){
                    type = question.getBType();
                }
                score.add(type);
            }
        }
        //获取结果
        String result = score.getCalcResult();

        score.setResult(result);

        //添加到表中
        mbtiTestMapper.insertMBTITestScore(score);

        return score.getResult();
    }

    @Override
    public TestReport getTestReport(String mbti) {

        TestReport testReport = mbtiTestMapper.selectReportByMBTI(mbti);

        List<MBTIIntro> list = mbtiTestMapper.selectIntro();

        String[] split = mbti.split("");

        System.out.println(Arrays.toString(split));

        System.out.println(list);

        for (MBTIIntro mbtiIntro : list) {
            String type = mbtiIntro.getType();
            if (split[0].equalsIgnoreCase(type)){
                testReport.setEITypeIntroduction(mbtiIntro.getIntro());
            }
            if (split[1].equalsIgnoreCase(type)){
                testReport.setSNTypeIntroduction(mbtiIntro.getIntro());
            }
            if (split[2].equalsIgnoreCase(type)){
                testReport.setTFTypeIntroduction(mbtiIntro.getIntro());
            }
            if (split[3].equalsIgnoreCase(type)){
                testReport.setJPTypeIntroduction(mbtiIntro.getIntro());
            }
        }

        return testReport;
    }
}