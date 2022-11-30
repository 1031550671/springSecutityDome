package com.springsercurity;

import com.springsercurity.domain.User;
import com.springsercurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void password(){
//        System.out.println(passwordEncoder.encode("user"));

        boolean matches = passwordEncoder.matches("user", "$2a$10$Hufk3weISXW0GM2./oNC5OkXV2bUgUzg.fgicH.u1MlCVQHZauAKG");

        System.out.println(matches);
    }

}
