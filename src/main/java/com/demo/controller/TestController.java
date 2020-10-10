package com.demo.controller;

import com.demo.entity.Test;
import com.demo.exception.CustomException;
import com.demo.exception.CustomExceptionType;
import com.demo.service.TestService;
import com.demo.service.impl.TestServiceimpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 测试全局统一异常
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService service;

    //GlobalResponseAdvice 里面会统一再封装为AjaxResponse

    /**
     * {
     * "isok": true,
     * "code": 200,
     * "message": "success",
     * "data": {
     * "id": 2,
     * "name": "test1"
     * }
     * }
     *
     * @param id
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/test")
    @ResponseBody
    public Test test1(Integer id) {
        Test test = service.selectByPrimaryKey(id);
        return test;
    }

    //或者返货这种类型   GlobalResponseAdvice 里面会统一再封装为AjaxResponse
    @GetMapping("listvo")
    @ResponseBody
    public List<Test> gettest() {
        return null;
    }

}
