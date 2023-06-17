package org.jxx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description: UserDetailsServiceImpl
 * date: 2023/6/17
 * author: javaear
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userMapper.getByName(username);
        JwtUser securityUser = new JwtUser(user);

        return securityUser;
    }

}
