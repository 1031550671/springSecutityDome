package com.springsercurity.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springsercurity.domain.LoginUser;
import com.springsercurity.unil.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token))
        {
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String id = JwtUtils.getClaim(token, "id").asString();
        //从rides获取用户信息
        Object tokenRedis = redisTemplate.opsForHash().entries("userInfo").get(id);
        if (Objects.isNull(tokenRedis))
        {
            throw new RuntimeException("token 为空，未登录");
        }
        String ss =  JSON.toJSONString(tokenRedis);
        LoginUser loginUser = JSONObject.parseObject(ss,LoginUser.class);

        //数据存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }
}
