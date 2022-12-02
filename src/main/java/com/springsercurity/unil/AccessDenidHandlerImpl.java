package com.springsercurity.unil;

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDenidHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result resultUnit =  new Result();
        resultUnit.setCode(401);
        resultUnit.setMsg(accessDeniedException.getLocalizedMessage());


        String string = JSON.toJSONString(resultUnit);
        Webutils.renderString(response,string);
    }
}
