package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MBTIResult {
    private String mbtiName;//MBTI测试结果
    private double IProportion;//I的比例
    private double EProportion;//E的比例
    private double SProportion;//S的比例
    private double NProportion;//N的比例
    private double TProportion;//T的比例
    private double FProportion;//F的比例
    private double JProportion;//J的比例
    private double PProportion;//P的比例

    public void calculateProportion(int I, int E, int S, int N, int T, int F, int J, int P) {
        int num = I + E + S + N + T + F + J + P;
        // 使用BigDecimal保留两位小数
        // 将保留两位小数后的值转为double
        IProportion = numConversion2Double(I,num);
        EProportion = numConversion2Double(E,num);
        SProportion = numConversion2Double(S,num);
        NProportion = numConversion2Double(N,num);
        TProportion = numConversion2Double(T,num);
        FProportion = numConversion2Double(F,num);
        JProportion = numConversion2Double(J,num);
        PProportion = numConversion2Double(P,num);
    }

    private double numConversion2Double(int value, int total) {
        return new BigDecimal(value * 1.0 / total).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
