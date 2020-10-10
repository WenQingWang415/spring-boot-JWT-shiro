package com.demo.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.demo.entity.Test;
import com.demo.entity.login;
import com.demo.service.TestService;
import com.demo.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountReaIm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    TestService service;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String userid = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        Test test=service.selectByPrimaryKey( Integer.parseInt(userid));
        if (test == null) {
            throw new UnknownAccountException("账户不存在");
        }
        login profile = new login();
        BeanUtil.copyProperties(test, profile);
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
