package com.Controller;

import com.pojo.Result;
import com.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/demo")
public class JwtDemo {

    @Autowired
    private HttpServletRequest req;

    @GetMapping("/demo1")
    public Result demo(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        Claims claims = JwtUtils.parseJwt(token);
        System.out.println(claims);
        return Result.success(1);
    }

    @GetMapping("/demo2")
    public Result demo1(){
        String jwt = req.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        System.out.println(claims);
        return Result.success(2);
    }
}
