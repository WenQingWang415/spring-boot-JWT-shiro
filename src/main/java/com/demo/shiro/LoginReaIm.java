package com.demo.shiro;


import cn.hutool.core.bean.BeanUtil;
import com.demo.entity.Test;
import com.demo.entity.login;
import com.demo.service.TestService;
import com.demo.utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class LoginReaIm extends AuthorizingRealm {

    @Autowired
    TestService service;
    @Autowired
    JwtUtils jwtUtils;
    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof 
                UsernamePasswordToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *shiro 身份验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userId = token.getPrincipal().toString();
       Test test=service.selectByPrimaryKey( Integer.parseInt(userId));
        if (test == null) {
            throw new UnknownAccountException("账户不存在");
        }
        login profile = new login();
        BeanUtil.copyProperties(test, profile);

        return new SimpleAuthenticationInfo(profile,test.getName(), getName());
    }
}
