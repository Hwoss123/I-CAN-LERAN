package com.Controller;

import com.Service.MBTITestService;
import com.pojo.Question;
import com.pojo.Result;
import com.utils.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/mbti")
@RestController
public class MBTITestController {

    @Autowired
    private MBTITestService mbtiTestService;


//   根据题目的获取个数进行返回
    @GetMapping("/{num}")
    public Result getMBTIQuestions(@PathVariable Integer num){
        log.info("题目的个数:{}",num);
        try{
            List<Question> list = mbtiTestService.getQuestionsByQNum(num);
            return Result.success(Code.MBTI_QUESTION_OK,list);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error(Code.MBTI_QUESTION_ERR,"获取题目失败");
        }

    }

}