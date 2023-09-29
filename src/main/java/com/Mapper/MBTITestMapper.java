package com.Mapper;

import com.pojo.Question;
import com.pojo.TypeCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface MBTITestMapper {

    //按TF SN JP EI的顺序排序
    @Select("select * from question order by type desc")
    List<Question> getQuestions();

    //计算每个type对应题目的个数
    @Select("SELECT type, COUNT(*) AS count FROM question GROUP BY type ORDER BY type DESC")
    List<TypeCount> getTypeCount();
}
