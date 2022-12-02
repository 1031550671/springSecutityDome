package com.springsercurity.service;

import com.springsercurity.domain.LoginUser;
import com.springsercurity.domain.userLoginDto;
import com.springsercurity.unil.JwtUtils;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;


     public String login (userLoginDto userLoginDto)
     {
         //用户认证
         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(),userLoginDto.getPwd());

         Authentication authenticate = authenticationManager.authenticate(authenticationToken);

         if (Optional.ofNullable(authenticate).equals(Optional.empty()))
         {
             throw new RuntimeException("登录失败");
         }
         LoginUser principal = (LoginUser)authenticate.getPrincipal();

         String id = principal.getUser().getId();
         String token = JwtUtils.getToken(id);
         redisTemplate.opsForValue().set(id,token,5000);
         redisTemplate.opsForHash().putIfAbsent("userInfo",id,principal);
         return token;
     }

    public void logout() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        redisTemplate.opsForHash().delete(id);



    }
}
