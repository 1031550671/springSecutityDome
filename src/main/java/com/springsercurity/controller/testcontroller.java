package com.springsercurity.controller;

import com.springsercurity.domain.userLoginDto;
import com.springsercurity.service.UserService;
import com.springsercurity.unil.ResultUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('管理员')")
    @GetMapping("index")
    @ResponseBody
    public ResponseEntity index(){
        return ResultUnit.success("登录成功");
    }


    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody userLoginDto userLoginDto){

        String login = userService.login(userLoginDto);
        return ResultUnit.success("成功",login);
    }

    @GetMapping("/logout")
    public ResponseEntity logOut(){
        userService.logout();
        return ResultUnit.success();
    }

}
