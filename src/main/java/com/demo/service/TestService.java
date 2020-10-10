package com.demo.service;

import com.demo.entity.Test;

import java.io.Serializable;

public interface TestService {
    Test selectByPrimaryKey(Integer id);

}
