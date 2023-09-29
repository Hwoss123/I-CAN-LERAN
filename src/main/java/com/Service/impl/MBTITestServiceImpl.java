package com.Service.impl;

import com.Mapper.MBTITestMapper;
import com.Service.MBTITestService;
import com.pojo.Question;
import com.pojo.TypeCount;
import com.utils.ExtractQuestionsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBTITestServiceImpl implements MBTITestService {

    @Autowired
    private MBTITestMapper mbtiTestMapper;

    @Override
    public List<Question> getQuestionsByQNum(Integer num) {

        List<Question> list =  mbtiTestMapper.getQuestions();

        List<TypeCount> typeCounts = mbtiTestMapper.getTypeCount();

        return ExtractQuestionsUtils.extractQuestions(list,typeCounts,num);
    }
}
