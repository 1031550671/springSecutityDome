package com.springsercurity.unil;

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 */

@Component
public class AuthenticationEntryPointImpl  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result resultUnit =  new Result();
        resultUnit.setCode(401);
        resultUnit.setMsg(authException.getLocalizedMessage());

        String string = JSON.toJSONString(resultUnit);
        Webutils.renderString(response,string);
    }
}
