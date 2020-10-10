package com.demo.controller;

import cn.hutool.core.map.MapUtil;
import com.demo.entity.Test;
import com.demo.entity.login;
import com.demo.exception.AjaxResponse;
import com.demo.exception.CustomExceptionType;
import com.demo.service.TestService;
import com.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/***
 * 测试Jwt整合shiro登录
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class LoginController {

    @Autowired
    TestService service;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/login")
    public AjaxResponse login(@RequestBody login login, HttpServletResponse response) {

        Test test1 = service.selectByPrimaryKey(login.getId());
        if (test1 == null) {
            return AjaxResponse.error(CustomExceptionType.USER_INPUT_ERROR, "用户名不存在");
        }
        if (!test1.getName().equals(login.getName())) {
            return AjaxResponse.error(CustomExceptionType.USER_INPUT_ERROR, "密码不正确");

        }
        String jwt = jwtUtils.generateToken(test1.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(String.valueOf(login.getId()), test1.getName());
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(usernamePasswordToken);
            return AjaxResponse.success(MapUtil.builder().put("id", test1.getId()).put("name", test1.getName()).map());
        }  catch (UnknownAccountException e) {
            return AjaxResponse.error(CustomExceptionType.USER_INPUT_ERROR, "用户名不存在");
        }

    }
    @RequiresAuthentication
    @GetMapping("/test1")
    @ResponseBody
    public Test test1(Integer id) {
        Test test = service.selectByPrimaryKey(id);
        return test;
    }

}
