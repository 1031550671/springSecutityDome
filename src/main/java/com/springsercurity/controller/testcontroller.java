package com.springsercurity.controller;

import com.springsercurity.domain.userLoginDto;
import com.springsercurity.service.UserService;
import com.springsercurity.unil.ResultUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class testcontroller {

    @Resource
    private UserService userService;

    @GetMapping("index")
    @ResponseBody
    public String index(){
        return "登录成功";
    }


    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody userLoginDto userLoginDto){

        return ResultUnit.success(userService.login(userLoginDto));
    }


}
