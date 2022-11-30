package com.springsercurity.sercurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springsercurity.domain.LoginUser;
import com.springsercurity.domain.User;
import com.springsercurity.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, username));

        if (Optional.ofNullable(user).equals(Optional.empty()))
        {
            throw new RuntimeException("用户名密码错误");
        }

        //对应权限

        return new LoginUser(user);
    }
}
