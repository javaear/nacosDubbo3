package org.jxx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: JwtUserDetailsService
 * date: 2023/6/17
 * author: javaear
 */
@Component
public class JwtUserDetailsService {

    @Autowired
    private UserMapper userMapper;


    public User loadUserByUsername(String userName) throws Exception {
        return  userMapper.getByName(userName);
    }
}
