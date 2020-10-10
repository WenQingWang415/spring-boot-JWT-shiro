package com.demo.service.impl;

import com.demo.dao.TestDao;
import com.demo.entity.Test;
import com.demo.exception.CustomException;
import com.demo.exception.CustomExceptionType;
import com.demo.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 测试全局统一异常
 */
@Service
@Log4j2
public class TestServiceimpl  implements TestService {
    @Resource
    protected TestDao testDao;


    @Override
    public Test selectByPrimaryKey(Integer id) {
        Test test= testDao.selectByPrimaryKey(id);
        return  test;
    }
}
