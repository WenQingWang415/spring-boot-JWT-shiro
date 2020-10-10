package com.demo.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 我们需要重写  AuthenticationToken接口 此接口的作用
 * AuthenticationToken: shiro中负责把username,password生成用于验证的token的封装类
 * 自定义一个对象用来封装token
 */
public class JwtToken  implements AuthenticationToken {
    private String token;
public  JwtToken (String token){
    this.token=token;
}
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
