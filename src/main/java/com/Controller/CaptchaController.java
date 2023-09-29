package com.Controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.Dao.Result;
import com.Dao.kaptcha;
import com.utils.ToolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Api(tags = "图形验证码-api")
@RequestMapping("/api")
@RestController
public class CaptchaController {

    // 模拟缓存
   static Map<String,String> cache = new HashMap<>();
   static Map<String, Long> expire = new HashMap<>();


    @ApiOperation("点击获取图形验证码")
    @GetMapping("/identifyImage")
    public Result identifyImage(
                              @ApiParam(value = "图形验证码id,无值：生成验证码，有值:刷新验证码")
                              @RequestParam(name = "codeId", required = false) String codeId,
                              @RequestParam(required = false)String UUID) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 90, 4, 100);
        // 验证码值
        String code = lineCaptcha.getCode();
        // 模拟把验证码的值存储到缓存
        if (codeId == null) {
//            System.out.println("获取图形码");
            codeId = ToolUtil.simpleUUID();
            // 保存图形码值
            cache.put(codeId, code);
//            这里面存的是UUID的图片对应，还有验证码
        } else {
            // 更新图形码值，此时此刻 图形码可能已经过期删除，那就相对于保存一个新的
            cache.put(codeId, code);
        }
        expire.put(codeId,  System.currentTimeMillis());


        kaptcha kaptcha = new kaptcha(codeId,lineCaptcha.getImageBase64Data());
        return Result.success(kaptcha);
    }
}