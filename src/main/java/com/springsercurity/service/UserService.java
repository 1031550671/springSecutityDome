package com.springsercurity.service;

import com.springsercurity.domain.userLoginDto;
import com.springsercurity.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthenticationManager authenticationManager;


     public  boolean login (userLoginDto userLoginDto)
     {
         //用户认证
         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(),userLoginDto.getPwd());

         Authentication authenticate = authenticationManager.authenticate(authenticationToken);

         if (Optional.ofNullable(authenticate).equals(Optional.empty()))
         {
             throw new RuntimeException("登录失败");
         }

         return true;
     }

}
